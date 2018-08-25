/**
 * 
 */

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncJobMonitor {

    private static final long INIT_TIME = System.currentTimeMillis();

    private Queue<Job> queue = new ConcurrentLinkedQueue<>();
    private Map<String, Long> endMap = new ConcurrentHashMap<>();

    public void start(Job job) {
        queue.add(job);
    }

    public void end(String id, long end) {
        endMap.put(id, end);
        cleanQueue();
    }

    private void cleanQueue() {
        while (!queue.isEmpty() && endMap.containsKey(queue.peek().id)) {
            Job curr = queue.poll();
            curr.end = endMap.get(curr.id);
            endMap.remove(curr.id);
            printJob(curr);
        }
    }

    private void printJob(Job job) {
        System.out.println(String.format("now: %s, job: %s, start: %s, end: %s",
                System.currentTimeMillis() - INIT_TIME,
                job.id,
                job.start - INIT_TIME,
                job.end - INIT_TIME));
    }

    static class Job {
        String id;
        long start;
        long end;
        Job(String id, long start) {
           this.id = id;
           this.start = start;
        }
    }

    public static void main(String[] args) {
        AsyncJobMonitor monitor = new AsyncJobMonitor();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        String[] ids = new String[]{"a", "b", "c", "d", "e"};
        long[] times = new long[]{5, 2, 5, 4, 1};
        for (int i=0; i<5; i++) {
            String id = ids[i];
            long time = times[i];
            executorService.execute(() -> {
                Job job = new Job(id, System.currentTimeMillis());
                monitor.start(job);
                try {
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                monitor.end(id, System.currentTimeMillis());
            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        executorService.shutdown();
    }

}
