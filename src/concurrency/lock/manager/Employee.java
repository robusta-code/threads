package concurrency.lock.manager;

/**
 * User: Nicolas Date: 13/10/12 Time: 22:41
 */
public class Employee {

	String name;
	int salary;
	Manager boss;

	public Employee(String name, int salary, Manager manager) {
		this.name = name;
		this.salary = salary;
		this.boss = manager;
	}

	public Thread doThreadTask(final Task task) {

		Thread t = new Thread() {
			@Override
			public void run() {

				try {

					long time = 500 + (long) Math.random() * 2000;
					System.out.println(name + " starts works on " + task);
					sleep(time);
					task.time = time;
					notifyBoss(task);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};
		t.start();
		return t;
	}

	public void notifyBoss(Task task) throws InterruptedException {
		System.out.println(this + " waiting for boss");
		TaskApplication.lock.lock();
		try{			
			System.out.println("unlocking "+this);	
			System.out.println(boss + " is available");
			boss.evaluateTask(task);
		}finally{
			TaskApplication.lock.unlock();
		}
		
	}

	public void doTask(Task task) throws InterruptedException {
		long time = 500 + (long) Math.random() * 2000;
		Thread.currentThread().sleep(time);
		task.time = time;
	}

	@Override
	public String toString() {
		return name;
	}

}
