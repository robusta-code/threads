package concurrency.waitnotify;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:28
 */
public class CommentApplication {

    public static void main(String[] args) {

        final CommentWriter writer = new CommentWriter();
        final CommentCounter counter = new CommentCounter();

        Thread writerThread = new Thread() {
            @Override
            public void run() {
                System.out.println("Starting writerThread");
                writer.slowlyWriteComment("I'm in the box");
            }
        };


        Thread counterThread = new Thread(){
            @Override
            public void run() {
                System.out.println("Starting counterThread");
                try {
                	sleep(15);
                    synchronized (writer){
                        System.out.println("Counter waits gently()");
                        writer.wait();
                        System.out.println("we have " + counter.fastCount() + " comments");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        writerThread.start();
        counterThread.setPriority(Thread.MIN_PRIORITY);
        counterThread.start();

    }
}


/*


==>>> Add notify() in CommentWriter.slowlyWriteComment()
*/