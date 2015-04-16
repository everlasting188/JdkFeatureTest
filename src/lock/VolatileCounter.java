package lock;

import java.util.concurrent.CountDownLatch;

/**
 *����ͬ��������£��᲻����dirtyд������ 
 */
public class VolatileCounter {

	public volatile static int count = 0;

	public static void inc() {
		// �����ӳ�5���룬ʹ�ý������
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		//synchronized(Counter.class) {
		count++;
		//}
	}

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1000);
		// ͬʱ����1000���̣߳�ȥ����i++���㣬����ʵ�ʽ��
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					VolatileCounter.inc();
					latch.countDown();
				}
			}).start();
		}
		latch.await();
		// ����ÿ�����е�ֵ���п��ܲ�ͬ,����Ϊ1000
		System.out.println("���н��:Counter.count=" + VolatileCounter.count);
	}
}