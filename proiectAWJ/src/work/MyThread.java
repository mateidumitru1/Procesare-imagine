package work;

public abstract class MyThread extends Thread {
	
	protected Buffer buf;

	public MyThread(Buffer b, String threadName) {
		
		super(threadName);
		buf = b;
		System.out.println("Constructor thread = " + threadName);
	}

	public void start() {
		
		System.out.println("Starting thread = " + getName());
		super.start();
	}
	
	public abstract void run();
}
