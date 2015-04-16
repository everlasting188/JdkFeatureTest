package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {
	Object data;
	volatile boolean cacheValid;
	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	void processCachedData() {
	     rwl.readLock().lock();
	     if (!cacheValid) {
	        // Must release read lock before acquiring write lock
	        rwl.readLock().unlock();
	        rwl.writeLock().lock();
	        // Recheck state because another thread might have acquired
	        //   write lock and changed state before we did.
	        if (!cacheValid) {
	          //data = ...
	          cacheValid = true;
	        }
	        // Downgrade by acquiring read lock before releasing write lock
	        rwl.readLock().lock();
	        rwl.writeLock().unlock(); // Unlock write, still hold read
	     }

	     //use(data);
	     rwl.readLock().unlock();
	   }
}
