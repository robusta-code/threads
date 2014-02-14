package concurrency.forkjoin;

import domain.Comment;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by  User: nicorama
 * Date: 10/09/13 ; Time: 12:54
 */
public class RenderTask extends RecursiveTask<StringBuilder> {



    int taskCursor;
    final int n;
    List<Comment> comments;


    public RenderTask(int cursor, List<Comment> comments) {
        this.taskCursor = cursor;
        this.n = comments.size();
        this.comments= comments;

    }

    @Override
    protected StringBuilder compute() {

        if (n <= ForkJoinApplication.threshold){
            System.out.println(Thread.currentThread()+"rendering task");
            return new CommentRenderer(comments).render();
        }


        int split = n/2;
        System.out.println(Thread.currentThread()+"Splitting at index "+taskCursor+ " from "+split+ " to "+n);
        RenderTask f1 = new RenderTask(taskCursor, comments.subList(0, split));
        f1.fork();
        RenderTask f2 = new RenderTask(taskCursor+split, comments.subList(split, n));

        return f2.compute().append(f1.join());
    }
}
