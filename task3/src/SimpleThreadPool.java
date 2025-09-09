import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool {
    private final int capacity;
    private final LinkedList<Priority> taskQueue = new LinkedList<>();
    private final List<Worker> workers = new ArrayList<>();
    private final Object lock = new Object();
    private volatile boolean isShutdown = false;
    private volatile boolean isStarted = false;

    public SimpleThreadPool(int capacity) {
        this.capacity = capacity;
    }

    public void execute(Runnable task, int priority) {
        synchronized (lock) {
            if (isShutdown) {
                throw new IllegalStateException("Пул завершён: новые задачи не принимаются");
            }
            taskQueue.add(new Priority(task, priority));
            taskQueue.sort(Comparator.comparingInt(t -> t.priority()));
        }
    }

    public void start() {
        synchronized (lock) {
            if (isStarted) return;
            isStarted = true;
            for (int i = 0; i < capacity; i++) {
                Worker worker = new Worker("Worker-" + i, taskQueue, lock, this);
                workers.add(worker);
                worker.start();
            }
        }
    }

    public void shutdown() {
        synchronized (lock) {
            isShutdown = true;
            lock.notifyAll();
        }
    }

    public void awaitTermination() {
        for (Worker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException ignored) {}
        }
    }

    public boolean isShutdown() {
        return isShutdown;
    }

    public boolean isTerminated() {
        return workers.stream().noneMatch(Thread::isAlive);
    }
}
