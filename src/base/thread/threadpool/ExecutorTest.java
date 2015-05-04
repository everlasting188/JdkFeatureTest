package base.thread.threadpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.transform.Result;
/**
 *Executors��Ҫ���ࣺ
 *      threadpool����͵��ȹ���
 *
 *completionService��Ҫ����������
 *      final Executor executor;                         //ִ����
 *      final AbstractExecutorService aes;               //����ʵ��
 *      final BlockingQueue<Future<V>> completionQueue;  //��ɵ���������
 *      
 * �޽����˵����
 * 		An unbounded Queue is one which is initialized without capacity, actually by default it initialized with Integer.MAX_VALUE 
 */
public class ExecutorTest {

	 
	public static void main(String[] args) {
		//testConn();
		testSingleThread();
	}
	
	/**
	 * java����hashMapѧϰ
	 */
	public static void testConn(){
		java.util.concurrent.ConcurrentHashMap<String, String>  testMap = new ConcurrentHashMap<>();
		for (int i = 0; i < 2; i++) {
			testMap.put(""+i, ""+i);
		}
		
		for (int i = 0; i < 2; i++) {
			String value = testMap.get(""+i);
			System.out.println(value);
		}
		
		int sshift = 0;
        int ssize = 1;
        while (ssize < 10) {
            ++sshift;
            ssize <<= 1;
            System.out.println("ssize is:" + ssize);
        }
		
		System.out.println("end!");
	}
	
	/**
	 * ���Ե����̵߳Ĵ���
	 */
	public static void testSingleThread(){
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				int sum = 0;
				while (true) {
					System.out.println(sum+" hello execute!!");
					sum++;
					if (sum == 200) {
						break;
					}
				}
			}
		});
		
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				int sum = 0;
				while (true) {
					System.out.println(sum + "===============");
					sum++;
					if (sum == 200) {
						break;
					}
				}
			}
		});
		
	}
	
	/**
	 * �����ύ�������
	 */
	public static void testCompletionService(){
		Executor executor = Executors.newFixedThreadPool(20);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				int i = 1;
				System.out.print("hello execute!!");
			}
		});
		
		try {
			Future<String> future = completionService.take();
			System.out.println(future);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ������е�
	 * @param e
	 * @param solvers
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void solveAll(Executor e, Collection<Callable<Result>> solvers)throws InterruptedException, ExecutionException {
	     CompletionService<Result> ecs
	         = new ExecutorCompletionService<Result>(e);
	     for (Callable<Result> s : solvers)
	         ecs.submit(s);
	     int n = solvers.size();
	     for (int i = 0; i < n; ++i) {
	         Result r = ecs.take().get();
	         if (r != null){
	        	 //use(r);
	         }
	     }
	}
	
	/**
	 * ���ֻ��һ���������Ļ���ֱ���˳�
	 * @param e
	 * @param solvers
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void solveOne(Executor e, Collection<Callable<Result>> solvers)throws InterruptedException, ExecutionException {
		  CompletionService<Result> ecs
          = new ExecutorCompletionService<Result>(e);
      int n = solvers.size();
      List<Future<Result>> futures
          = new ArrayList<Future<Result>>(n);
      Result result = null;
      try {
          for (Callable<Result> s : solvers)
              futures.add(ecs.submit(s));
          for (int i = 0; i < n; ++i) {
              try {
                  Result r = ecs.take().get();
                  if (r != null) {
                      result = r;
                      break;
                  }
              } catch (ExecutionException ignore) {}
          }
      }
      finally {
          for (Future<Result> f : futures)
              f.cancel(true);
      }

      if (result != null){
          //use(result);
      }
	}
	

}
