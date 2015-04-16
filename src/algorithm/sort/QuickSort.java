package algorithm.sort;

import oracle.spatial.network.PartitionCache;

public class QuickSort {

	public static void main(String[] args) {
		

	}
	
	
	public static void sort(Comparable<TestInteger>[] a){
		//StdRandom.shuffle(a);
		sort(a,0,a.length - 1);
	}
	
	private static void sort(Comparable[] input ,int lo,int hi){
		/**if(hi <= hi) return;
		//int j = partition(input,lo,hi);
		sort(input,lo,j - 1);
		sort(input,j+1,hi);
		*/
	}
	
	private static int partition(Comparable[] input ,int lo,int hi){
		int i = lo;
		int j = hi;
		Comparable<TestInteger> v=input[lo];
		
		return 1;
	}
	
	
	class TestInteger{
		private int value;
		
		public  TestInteger(int iValue){
			value = iValue;
		}
		
		
		public int getValue() {
			return value;
		}


		public void setValue(int value) {
			this.value = value;
		}
		
	}
	
}
