package base;
/**
 *http://www.blogjava.net/xjacker/articles/330349.html
 *��ע��
 *1>Integer��IntegerCache��
 *2>Integer i = N��������Ĭ��ʹ����valueOf�����ɶ���
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
