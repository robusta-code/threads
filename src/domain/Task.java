package domain;

import java.util.Random;

/**
 * Created by Nicolas
 * Date: 19/09/13
 * Time: 15:32
 */
public class Task {

    long time;
    String name;

    public Task() {

    }

    public Task(String name) {
        System.out.println("doing task "+name);
        doRandomTask(100, 500);

    }

    public long doTask(){
        long time = System.currentTimeMillis();
        String str ="";
        for (int i = 0 ; i< 3000 ; i++){
            str = str.toUpperCase();
            str = str.toLowerCase();
            str = "STRING"+str.toLowerCase();
        }
        this.time = System.currentTimeMillis()-time;
        return this.time;
    }



    public long getTime() {
        return time;
    }

    public long doTask(String name, long time){

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;
    }

    public long doTask(long time){

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;
    }

    public long doRandomTask(int minimum, int maximum){

        long time = new Random().nextInt(maximum);
        time = time < minimum ? minimum : time;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;
    }

    @Override
    public String toString() {
        return "Task "+this.name+" took "+time;
    }

    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        new Task("Some task");
        System.out.println(System.currentTimeMillis()-t);
    }
}
