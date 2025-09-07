import annotations.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    private static int passed = 0;
    private static int failed = 0;
    private static BufferedWriter logWriter;


    public static void runTests(Class<?> testClass) throws IOException {
        try {
            logWriter = new BufferedWriter(new FileWriter("task1/src/log/test-log.txt"));
            Method beforeSuite = null;
            Method afterSuite = null;
            List<Method> testMethods = new ArrayList<>();
            List<Method> afterTestMethods = new ArrayList<>();
            List<Method> beforeTestMethods = new ArrayList<>();

            for (Method method : testClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(BeforeSuite.class)) {
                    if (beforeSuite != null)
                        throw new RuntimeException("Допускается только один метод с аннотацией @BeforeSuite");
                    if (!Modifier.isStatic(method.getModifiers()))
                        throw new RuntimeException("@BeforeSuite должен быть static");
                    beforeSuite = method;
                } else if (method.isAnnotationPresent(AfterSuite.class)) {
                    if (afterSuite != null)
                        throw new RuntimeException("Допускается только один метод с аннотацией @AfterSuite");
                    if (!Modifier.isStatic(method.getModifiers()))
                        throw new RuntimeException("@AfterSuite должен быть static");
                    afterSuite = method;
                } else if (method.isAnnotationPresent(BeforeTest.class)) {
                    beforeTestMethods.add(method);
                } else if (method.isAnnotationPresent(AfterTest.class)) {
                    afterTestMethods.add(method);
                } else if (method.isAnnotationPresent(Test.class)) {
                    testMethods.add(method);
                }
            }

            testMethods.sort((m1, m2) -> Integer.compare(
                    m2.getAnnotation(Test.class).priority(),
                    m1.getAnnotation(Test.class).priority()
            ));

            if (beforeSuite != null) {
                    beforeSuite.invoke(null);
            }
            Object instance = testClass.getDeclaredConstructor().newInstance();

            for (Method testMethod : testMethods) {
                if (testMethod.isAnnotationPresent(Ignore.class)) {
                    log("IGNORED: " + testMethod.getName());
                    continue;
                }

                for (Method beforeTest : beforeTestMethods) {
                        beforeTest.invoke(instance);
                }

                try {
                    if (testMethod.isAnnotationPresent(CsvSource.class)) {
                        String csv = testMethod.getAnnotation(CsvSource.class).value();
                        String[] parts = csv.split(",");
                        Object[] args = new Object[parts.length];
                        Class<?>[] paramTypes = testMethod.getParameterTypes();
                        for (int i = 0; i < parts.length; i++) {
                            String raw = parts[i].trim();
                            Class<?> type = paramTypes[i];
                            args[i] = parserValue(type, raw);
                        }

                        testMethod.invoke(instance, args);
                    } else {
                        testMethod.invoke(instance);
                    }

                    log("PASSED: " + testMethod.getName());
                    passed++;
                } catch (Exception e) {
                    log("FAILED: " + testMethod.getName());
                    log(" Exception: " + e.getCause());
                    failed++;
                }

                for (Method afterTest : afterTestMethods) afterTest.invoke(instance);
            }

            if(afterSuite != null) afterSuite.invoke(null);

            log("--- TEST SUMMARY ---");
            log("PASSED: " + passed);
            log("FAILED: " + failed);
            logWriter.close();
        }catch (Exception e){
            throw new RuntimeException("Test execution failed", e);
        }
    }

    private static Object parserValue(Class<?> type, String value) {
        if (type == int.class) return Integer.parseInt(value);
        if (type == boolean.class) return Boolean.parseBoolean(value);
        if (type == String.class) return value;
        if (type == double.class) return Double.parseDouble(value);
        throw new IllegalArgumentException("Unsupported type: " + type);
    }

    private static void log(String message) throws IOException {
        System.out.println(message);
        logWriter.write(message);
        logWriter.newLine();
    }
}
