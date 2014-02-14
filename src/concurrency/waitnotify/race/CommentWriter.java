package concurrency.waitnotify.race;

import domain.Comment;
import domain.CommentDataSource;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:22
 */
public class CommentWriter {


	public boolean started =false;
    public void slowlyWriteComment(String content) {


    	
        try {

            int time =new Random().nextInt(1000);
            synchronized (this){
            	started = true;
                System.out.println("Writing database in "+time+ "ms");
                CommentDataSource.comments.add(new Comment(content));
                notifyAll();
            }


             time = new Random().nextInt(5000);
            System.out.println("Now I'm making not critical long admin stupid stuff for " + time + " ms");
            sleep(time <2000 ? 2000 : time);
            System.out.println("I've finished my not critical long admin stupid stuff");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
