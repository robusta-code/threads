package concurrency.lock.manager;

/**
 * User: Nicolas
 * Date: 13/10/12
 * Time: 22:41
 */
public class Manager {

    Employee[] employees;

    public Task createTask(int i){

        return new Task("Task "+(i+1), i, this, employees[i]);
    }

    public double evaluateTask(Task task){
        double dif = task.difficulty;
        double value = ((dif+1)*1000000/task.time/task.employee.salary);
        System.out.println(task +" has been evaluated to : "+value);
        return value;
    }



    public void meeting(){
        final Manager boss = this;
        Thread t = new Thread(){
            @Override
            public void run() {
            	TaskApplication.lock.lock();
                try {
                    long time =1000;
                    System.out.println("The boss is on meeting for "+time);
                    //        onWait();
                    sleep(time);
                    System.out.println("meeting ended");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                	System.out.println("The lock has "+TaskApplication.lock.getQueueLength()+" waiters");
                	
                	TaskApplication.lock.unlock();
                }

            }
        };
        t.start();

    }

    @Override
    public String toString() {
        return "The Boss";
    }
}
