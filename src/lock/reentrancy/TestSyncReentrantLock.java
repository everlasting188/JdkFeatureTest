package lock.reentrancy;

import java.util.concurrent.locks.ReentrantLock;

public class TestSyncReentrantLock implements Runnable {
	private ReentrantLock rLock = new ReentrantLock();

	public void get() {
		try {
			rLock.lock();
			System.out.println(Thread.currentThread().getId() + ": get()");
			set();
		} finally {
			rLock.unlock();
		}

	}

	//public synchronized void set() {
	public void set() {
		try {
			rLock.lock();
			System.out.println(Thread.currentThread().getId() + ": set()");
		} finally {
			rLock.unlock();
		}

	}

	@Override
	public void run() {
		get();
	}

	public static void main(String[] args) {
		TestSyncReentrantLock ss = new TestSyncReentrantLock();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}
}
