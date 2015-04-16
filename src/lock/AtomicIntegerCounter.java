package lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


public class AtomicIntegerCounter {
	   public  static AtomicInteger count = new AtomicInteger(0);
		
	    public static void inc() {
	 
	        //�����ӳ�1���룬ʹ�ý������
	        try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	        }
	        count.getAndIncrement();
	    }
	 
	    public static void main(String[] args) throws InterruptedException {
	    	
	    	final CountDownLatch latch = new CountDownLatch(1000);
	        //ͬʱ����1000���̣߳�ȥ����i++���㣬����ʵ�ʽ��
	        for (int i = 0; i < 1000; i++) {
	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	AtomicIntegerCounter.inc();
	                    latch.countDown();
	                }
	            }).start();
	        }
	        latch.await();
	        //����ÿ�����е�ֵ���п��ܲ�ͬ,����Ϊ1000
	        System.out.println("���н��:Counter.count=" + AtomicIntegerCounter.count);
	    }
	}