package concurrency.lock.condition;

import domain.Comment;
import domain.CommentDataSource;
import domain.Task;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nicolas
 * Date: 09/09/13
 * Time: 13:22
 */
public class CommentWriter {


    ReentrantLock lock = CommentConditionApplication.lock;
    Condition isFirst = lock.newCondition();
    boolean started = false;

    public void slowlyWriteComment(String content) {
        long time;

        lock.lock();
        System.out.println("Writer has lock");
        started = true;

        try {
            Task task = new Task("Saving in database");
            System.out.println("Writing database in " + task.getTime() + "ms");
            CommentDataSource.comments.add(new Comment(content));
        } finally {
            isFirst.signalAll();
            lock.unlock();
            System.out.println("Writer released lock");
        }


        System.out.println("Now I'm tweeting about my comments " +
                "&  making backups in five continents");
        time = new Task().doRandomTask(2000, 5000);
        System.out.println("I've finished my tweets & backups in "+time+" ms");


    }
}
