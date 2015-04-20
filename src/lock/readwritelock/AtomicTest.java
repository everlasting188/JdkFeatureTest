package lock.readwritelock;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicTest {

	public static void main(String[] args) {
		AtomicBoolean  isInit = new AtomicBoolean(false);
		if (isInit.compareAndSet(false, true)) {
			System.out.println("is false");
		}
		
		System.out.println(isInit.get());
	}

}
