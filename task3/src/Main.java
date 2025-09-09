public class Main {
    public static void main(String[] args) {
        SimpleThreadPool pool = new SimpleThreadPool(3);

        pool.execute(() -> System.out.println("Задача A (приоритет 5) " + Thread.currentThread().getName()), 5);
        pool.execute(() -> System.out.println("Задача B (приоритет 1) " + Thread.currentThread().getName()), 1);
        pool.execute(() -> System.out.println("Задача C (приоритет 10) " + Thread.currentThread().getName()), 10);
        pool.execute(() -> System.out.println("Задача D (приоритет 0) " + Thread.currentThread().getName()), 0);
        pool.execute(() -> System.out.println("Задача E (приоритет 4) " + Thread.currentThread().getName()), 5);
        pool.execute(() -> System.out.println("Задача F (приоритет 3) " + Thread.currentThread().getName()), 3);
        pool.execute(() -> System.out.println("Задача G (приоритет 9) " + Thread.currentThread().getName()), 9);
        pool.execute(() -> System.out.println("Задача H (приоритет 8) " + Thread.currentThread().getName()), 8);
        pool.execute(() -> System.out.println("Задача I (приоритет 6) " + Thread.currentThread().getName()), 6);
        pool.execute(() -> System.out.println("Задача Y (приоритет 7) " + Thread.currentThread().getName()), 7);
        pool.execute(() -> System.out.println("Задача K (приоритет 2) " + Thread.currentThread().getName()), 2);

        pool.start();

        pool.shutdown();
        pool.awaitTermination();

        System.out.println("Все задачи завершены");
    }
}