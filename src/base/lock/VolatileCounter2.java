package base.lock;

/**
 *不会出现脏读的情况，单个线程写没有脏写的问题
 */
public class VolatileCounter2 {

	public volatile static int count = 0;
	
	public static int inc() {
		// 这里延迟5毫秒，使得结果明显
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		
		//synchronized(Counter.class) {
		count++;  //从主存读，然后写
		return count;
		//}
	}
	
	public static int read(){
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("inc:" + VolatileCounter2.inc());
				}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("read:"+VolatileCounter2.read());
				}
				
			}
		}).start();
	}
}
