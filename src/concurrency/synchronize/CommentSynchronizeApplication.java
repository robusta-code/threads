package concurrency.synchronize;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:28
 */
public class CommentSynchronizeApplication {

    public static void main(String[] args) {

        final Object lock = new Object();
        final CommentWriter writer = new CommentWriter();

        final CommentCounter counter = new CommentCounter();
        

        Thread writerThread = new Thread() {
            @Override
            public void run() {

                System.out.println("Starting writerThread");
                synchronized (lock){
                    System.out.println("writer got the lock");
                    writer.slowlyWriteComment("I'm in the box");
                }
            }
        };
        Thread counterThread = new Thread() {
            @Override
            public void run() {

                System.out.println("Starting counterThread");
                System.out.println("Starting counterThread");
                System.out.println("Starting counterThread");
                System.out.println("Starting counterThread");
                System.out.println("Starting counterThread");
                System.out.println("Starting counterThread");
                synchronized (lock){
                    System.out.println("counter got the lock");
                    System.out.println("we have " + counter.fastCount() + " comments");
                    //System.out.println("But it looks we have the bad number :(");
                }
            }
        };

        counterThread.start();
        writerThread.start();

    }
}


/*
Modify the Race conditions
Déplacer la position du    final CommentCounter counter = new CommentCounter();

*/