package lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 ������������˵��
1������ͬһ�߳�������lock() ���ᵼ�µڶ��ε���lockλ�ý�������������������
˵������������ǿ�����ġ�����lock�����ڣ�Ӧ��֤�߳��Ƿ�Ϊ�Ѿ���������̣߳�
2����1�����Ѿ��������unlock������һ�ε���ʱ�����Ѿ������ͷ��ˡ�ʵ���ϲ�Ӧ�ͷ�����
�����ü����ν���ͳ�ƣ�
 *
 */
public class BedSpinLock {
	private AtomicReference<Thread> owner = new AtomicReference<>();

	public void lock() {
		Thread current = Thread.currentThread();
		while (!owner.compareAndSet(null, current)) {
		}
	}

	public void unlock() {
		Thread current = Thread.currentThread();
		owner.compareAndSet(current, null);
	}
}
