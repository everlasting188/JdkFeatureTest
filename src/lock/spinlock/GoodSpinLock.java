package lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;
/**
 *解决了可冲入性的锁
 *java线程是基于“每线程（per-thread）”，而不是基于“每调用（per-invocation）”的（java中线程获得对象锁的操作是以每线程为粒度的，per-invocation互斥体获得对象锁的操作是以每调用作为粒度的）  
 */
public class GoodSpinLock {
	private AtomicReference<Thread> owner = new AtomicReference<>();
	private int count = 0;

	public void lock() {
		Thread current = Thread.currentThread();
		if (current == owner.get()) {
			count++;
			return;
		}

		while (!owner.compareAndSet(null, current)) {

		}
	}

	public void unlock() {
		Thread current = Thread.currentThread();
		if (current == owner.get()) {
			if (count != 0) {
				count--;
			} else {
				owner.compareAndSet(current, null);
			}

		}

	}
}
