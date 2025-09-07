package tests;

import annotations.*;

public class MyTestClass {
    @BeforeSuite
    public static void setupSuite() {
        System.out.println("==> Setup Suite");
    }

    @BeforeTest
    public void beforeEachTest() {
        System.out.println("  -> Before Test");
    }

    @Test(priority = 10)
    public void testA() {
        System.out.println("  -> Test A");
    }

    @Test(priority = 5)
    @Ignore
    public void testIgnored() {
        System.out.println("  -> This should not run");
    }

    @Test(priority = 7)
    @CsvSource("42, Hello, 3, true")
    public void testWithParams(int a, String b, int c, boolean d) {
        System.out.println("  -> Param Test: " + a + ", " + b + ", " + c + ", " + d);
    }

    @AfterTest
    public void afterEachTest() {
        System.out.println("  -> After Test");
    }

    @AfterSuite
    public static void tearDownSuite() {
        System.out.println("==> Tear Down Suite");
    }
}
