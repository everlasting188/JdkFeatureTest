import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length)
				notFull.await();
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}

//java对象LockSupport使用
class FIFOMutex {
	   private final AtomicBoolean locked = new AtomicBoolean(false);
	   private final Queue<Thread> waiters
	     = new ConcurrentLinkedQueue<Thread>();

	   public void lock() {
	     boolean wasInterrupted = false;
	     Thread current = Thread.currentThread();
	     waiters.add(current);

	     // Block while not first in queue or cannot acquire lock
	     while (waiters.peek() != current ||
	            !locked.compareAndSet(false, true)) {
	        LockSupport.park(this);
	        if (Thread.interrupted()) // ignore interrupts while waiting
	          wasInterrupted = true;
	     }

	     waiters.remove();
	     if (wasInterrupted)          // reassert interrupt status on exit
	        current.interrupt();
	   }

	   public void unlock() {
	     locked.set(false);
	     LockSupport.unpark(waiters.peek());
	   }
	   
	   //查看bug号
	   
	   //测试提交bug号
	   
	   //不提交bug号
	 }

