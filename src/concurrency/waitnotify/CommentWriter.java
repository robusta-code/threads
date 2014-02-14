package concurrency.waitnotify;

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


    public void slowlyWriteComment(String content) {
        int time =new Random().nextInt(1000);

        try {

            synchronized (this){
            

                System.out.println("Writing database in "+time+ "ms");
                CommentDataSource.comments.add(new Comment(content));
                notify();
            }


             time = new Random().nextInt(8000);
            System.out.println("Now I'm making not critical long admin stupid stuff for " + time + " ms");
            sleep(time <2000 ? 2000 : time);
            System.out.println("I've finished my not critical long admin stupid stuff");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
