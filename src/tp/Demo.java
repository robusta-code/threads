package tp;

public class Demo {

	
	public static void main(String[] arg){
	
		Thread t1 = new Thread(){
			public void run() {
				System.out.println("Je executé par le thread 1");
			};
		};
		
		Thread t2 = new Thread(){
			public void run() {
				System.out.println("Je executé par le thread 2");
			};
		};

		t1.start();
		t2.start();
		
	}
	
}
