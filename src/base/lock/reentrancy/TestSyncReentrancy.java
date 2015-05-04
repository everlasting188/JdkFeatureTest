package base.lock.reentrancy;

public class TestSyncReentrancy implements Runnable {

	public synchronized void get() {
		System.out.println(Thread.currentThread().getId()+": get()");
		set();
	}

	public synchronized void set() {
		System.out.println(Thread.currentThread().getId() +": set()");
	}

	@Override
	public void run() {
		get();
	}

	public static void main(String[] args) {
		TestSyncReentrancy ss = new TestSyncReentrancy();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}
}
