package base.lock.deadlock;
/**
 *子类相关的处理
 */
public class ParentLock {
	public synchronized void dosomething() {
		System.out.println("hello parent!!");
	}
	
	static class ChildrenLock extends ParentLock {
		public synchronized void dosomething() {
			System.out.println("children objects");
			super.dosomething();
		}
	}
	
	public static void main(String[] args) {
		ChildrenLock childrenLock = new ChildrenLock();
		childrenLock.dosomething();
	}
}

