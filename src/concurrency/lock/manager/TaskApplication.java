package concurrency.lock.manager;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Nicolas
 * Date: 13/10/12
 * Time: 23:01
 */
public class TaskApplication {

    static final  Manager boss = new Manager();
    static final public ReentrantLock lock = new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {
    	
    
        final Employee john = new Employee("John", 2000, boss);
        final Employee jim = new Employee("Jim", 1200, boss);
        final Employee jack = new Employee("Jack", 2800, boss);


        boss.employees = new Employee[]{john, jim, jack};
        Thread[] threads = new Thread[3];

        for (int i = 0 ; i < 3 ; i++){
            final Task task = boss.createTask(i);
            final Employee employee = boss.employees[i];
            threads[i] = employee.doThreadTask(task);
        }// end of for

        boss.meeting();

        for (int i = 0 ; i <3 ; i++){
            threads[i].join();
        }

    }
}
