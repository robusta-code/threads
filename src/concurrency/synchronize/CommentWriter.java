package concurrency.synchronize;

import domain.Comment;
import domain.CommentDataSource;
import domain.Task;

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




                new Task(content);

                System.out.println("Writing database in "+time+ "ms");
                CommentDataSource.comments.add(new Comment(content));
        System.out.println("comment written : "+content);



             time = new Random().nextInt(5000);
            System.out.println("Now I'm making not critical long admin stupid stuff for " + time + " ms");
            new Task("long admin stuff");
            System.out.println("I've finished my not critical long admin stupid stuff");


    }
}
