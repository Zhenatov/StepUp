import tests.MyTestClass;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TestRunner.runTests(MyTestClass.class);
    }
}