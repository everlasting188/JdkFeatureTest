package base.lock;

/**
 *����������������������߳�дû����д������
 */
public class VolatileCounter2 {

	public volatile static int count = 0;
	
	public static int inc() {
		// �����ӳ�5���룬ʹ�ý������
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		
		//synchronized(Counter.class) {
		count++;  //���������Ȼ��д
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
