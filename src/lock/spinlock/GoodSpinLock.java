package lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;
/**
 *����˿ɳ����Ե���
 *java�߳��ǻ��ڡ�ÿ�̣߳�per-thread�����������ǻ��ڡ�ÿ���ã�per-invocation�����ģ�java���̻߳�ö������Ĳ�������ÿ�߳�Ϊ���ȵģ�per-invocation�������ö������Ĳ�������ÿ������Ϊ���ȵģ�  
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
