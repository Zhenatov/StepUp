import java.util.LinkedList;

public class Worker extends Thread {
    private final LinkedList<Priority> taskQueue;
    private final Object lock;
    private final SimpleThreadPool pool;

    public Worker(String name, LinkedList<Priority> taskQueue, Object lock, SimpleThreadPool pool) {
        super(name);
        this.taskQueue = taskQueue;
        this.lock = lock;
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            Priority task;
            synchronized (lock) {
                while (taskQueue.isEmpty()) {
                    if (pool.isShutdown()) return;
                    try {
                        lock.wait();
                    } catch (InterruptedException ignored) {}
                }
                task = taskQueue.removeFirst();
            }
            try {
                task.runnable().run();
            } catch (Exception e) {
                System.err.println(getName() + " — ошибка: " + e.getMessage());
            }
        }
    }
}

