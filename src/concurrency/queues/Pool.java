package concurrency.queues;

import java.util.concurrent.*;

/**
 * Created by Nicolas Zozol
 * Date: 17/10/12
 */
public class Pool {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int maxThreads = 2;


        ThreadPoolExecutor executorService =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
                     
      Future<Long> f = executorService.submit(new LongCallable(10));
        Future<Long> g = executorService.submit(new LongCallable(12));
        Future<Long> h = executorService.submit(new LongCallable(13));
        Future<Long> i = executorService.submit(new LongCallable(13));
        Future<Long> j = executorService.submit(new LongCallable(13));
        Future<Long> k = executorService.submit(new LongCallable(13));

        // do Stuff


        System.out.println(i.get());
      System.out.println(f.get());

    }
}


class LongCallable implements Callable<Long> {
    
    long max;
    public LongCallable(long max){this.max = max;}
        
    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (long i = 0; i <= max; i++) {
            sum += i;
        }
        return sum;
    }
}


class RunnableRequest implements Runnable{

    long result;
    String query;
    Jdbc jdbc = Jdbc.instance;

    RunnableRequest(String query) {
        this.query = query;
    }

    @Override
    public void run() {
        this.result = jdbc.execute(query);
    }
    
}
