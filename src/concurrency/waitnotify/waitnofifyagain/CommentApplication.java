package concurrency.waitnotify.waitnofifyagain;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:28
 */
public class CommentApplication {

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

                System.out.println("we have " + counter.fastCount() + " comments");

            }
        };

        synchronized (writer) {
            writerThread.start();
            writer.wait();
            counterThread.start();
        }

    }
}


/*
 Thread counterThread = new Thread(){

        };

==>>> Add notify() in CommentWriter.slowlyWriteComment()
*/