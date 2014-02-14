package concurrency.forkjoin;

import domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by  User: nicorama
 * Date: 10/09/13 ; Time: 10:23
 */
public class ForkJoinApplication {

    final static int threshold= 4;
    final static int poolsize=3;
    final static int commentsNumber=80;

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        long t = System.currentTimeMillis();
        List<Comment> comments = new ArrayList<Comment>();
        for (int i = 0 ; i < commentsNumber ; i++){
            comments.add(new Comment("comment " +i));
        }
        ForkJoinPool pool = new ForkJoinPool(poolsize);
        RenderTask initialTask = new RenderTask(0, comments);
        pool.invoke(initialTask);
        System.out.println(initialTask.get());
        System.out.println(System.currentTimeMillis()-t);
    }
}
