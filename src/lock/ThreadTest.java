package lock;

public class ThreadTest {

	public static void main(String[] args) {
new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(1000);
						System.out.println("22222!!");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(1000);
						System.out.println("22222!!");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		}).start();
		
		System.out.println("exit!!");
	}

}
