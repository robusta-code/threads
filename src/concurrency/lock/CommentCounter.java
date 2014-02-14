package concurrency.lock;

import domain.CommentDataSource;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:22
 */
public class CommentCounter {

    public int fastCount(){
        try {

            int time =new Random().nextInt(200);
            sleep(time);
            System.out.println("Counting in "+time+ "ms");
            return CommentDataSource.comments.size();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
