package base;
/**
 *http://www.blogjava.net/xjacker/articles/330349.html
 *关注：
 *1>Integer的IntegerCache类
 *2>Integer i = N，编译器默认使用了valueOf来生成对象
 */
public class IntegerTest {
	
	public static void main(String[] args) {
		Integer  a = 1;
		Integer  b = 1;
		
		Integer  c = 200;
		Integer  d = 200;
		
		Integer  e = 100;
		Integer  f = 100;
		
		System.out.println(a==b);
		System.out.println(c == d);
		System.out.println(e == f);
	}

}
