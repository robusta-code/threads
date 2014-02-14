package concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:28
 */
public class CommentLockApplication {

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
                try {


                    while (!writer.lock.isLocked()) {
                        Thread.sleep(1); //VERY BAD solution
                    }
                    writer.lock.lock();
                    try{
                        System.out.println("Counter thread has now lock");
                        System.out.println("looks we have " + counter.fastCount() + " comments");
                    }finally {
                        writer.lock.unlock();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };


        counterThread.start();
        writerThread.start();


    }
}

