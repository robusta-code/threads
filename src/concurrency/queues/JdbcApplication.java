package concurrency.queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nicolas Zozol Date: 17/10/12
 */
public class JdbcApplication {

	public static void main(String[] args) {

		new Request("jo", "add jo");
		new Request("jack", "delete jack");
		new Request("jo2", "select jo");
		new Request("jack2", "add jack");

	}
}

class Request extends Thread {

	String query;
	Jdbc jdbc = Jdbc.instance;

	Request(String name, String query) {
		super(name);
		this.query = query;
		this.start();
	}

	@Override
	public void run() {

		System.out.println(getName() + " is waiting");
		synchronized (jdbc) {
			System.out.println(getName() + " is runnig request");
			jdbc.execute(query);
		}

	}
}

class Jdbc {

	static public Jdbc instance = new Jdbc();

	public long execute(String query) {
		try {

			System.out.println("Jdbc executes " + query + "in thread "+Thread.currentThread().getName());
			
			Thread.sleep(500);
			System.out.println("Jdbc ended");

		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return query.length();
	}
}