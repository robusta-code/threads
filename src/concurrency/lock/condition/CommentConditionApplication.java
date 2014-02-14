package concurrency.lock.condition;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:28
 */
public class CommentConditionApplication {

    static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {

        final CommentWriter writer = new CommentWriter();
        final CommentCounter counter = new CommentCounter();


        Thread writerThread = new Thread() {
            @Override
            public void run() {
                System.out.println("Starting writerThread");
                writer.slowlyWriteComment("I'm in the box");
            }
        };


        Thread counterThread = new Thread() {
            @Override
            public void run() {
                System.out.println("Starting counterThread");
                lock.lock();
                System.out.println("Counter thread has now lock");
                try {
                    if (!writer.started) {
                        System.out.println("Counter condition prefers waiting");
                        writer.isFirst.await();
                        System.out.println("Now Counter continues");
                    }
                    System.out.println("looks we have "
                            + counter.fastCount() + " comments");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };


        counterThread.start();
       // Thread.sleep(50);
        writerThread.start();

    }


}

