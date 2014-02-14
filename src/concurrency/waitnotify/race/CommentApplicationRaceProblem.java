package concurrency.waitnotify.race;


/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:55
 */
public class CommentApplicationRaceProblem {
    public static void main(String[] args) {

        final CommentWriter writer = new CommentWriter();
        final CommentCounter counter = new CommentCounter();

        Thread writerThread = new Thread() {


            @Override
            public void run() {
                System.out.println("Starting writerThread");

                String s="";
                for (int i=0; i<30;i++ ){s+=i;

                }
                System.out.println(s);
                writer.slowlyWriteComment("I'm in the box");
            }
        };


        Thread counterThread = new Thread(){
            @Override
            public void run() {
                System.out.println("Starting counterThread");
                try {
                    synchronized (writer){
                        
                        if (!writer.started){
                        	System.out.println("Counter waits gently()");
                        	writer.wait();
                        }                        
                        System.out.println("we have " + counter.fastCount() + " comments");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        writerThread.start();
        counterThread.start();

    }
}
