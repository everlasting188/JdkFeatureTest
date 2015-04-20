package distribution.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试一致性hash的类，相关类还有一定的bug
 */
public class TestConsistentHash {

	public static void main(String[] args) {
		testBigData();
	}

	/**
	 * 基础测试
	 */
	public static void testBase() {
		List<PhysicalNode> nodes = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			TestCache cacheMap = new TestCache();
			PhysicalNode node = new PhysicalNode(i, cacheMap);
			nodes.add(node);
		}

		ConsistentHash hash = new ConsistentHash(nodes);

		hash.put("111", "first");
		hash.put("222", "2first");
		hash.put("333", "3first");

		System.out.println(hash.get("111"));
		System.out.println(hash.get("222"));
		System.out.println(hash.get("333"));
		
		for (int i = 0; i < 100000; i++) {
			hash.put("" +i, ""+i+" =----");
		}
	}
	
	//大数据量测试
	public static void testBigData() {
		List<PhysicalNode> nodes = new ArrayList<>();

		for (int i = 1; i < 21; i++) {
			TestCache cacheMap = new TestCache();
			PhysicalNode node = new PhysicalNode(5*i, cacheMap);
			nodes.add(node);
		}

		ConsistentHash hash = new ConsistentHash(nodes);
		for (int i = 0; i < 100000; i++) {
			Random  random = new Random();
			int value = random.nextInt();
			hash.put("" +value, ""+value+" =----");
		}
		
		System.out.println("============before remove data================");
		for (PhysicalNode node:nodes) {
			System.out.println(node.getNodeId()+":"+node.size());
		}
		
		PhysicalNode deletNode = new PhysicalNode(5,null);
		hash.removeNode(deletNode);
		
		System.out.println("============after remove data================");
		for (int i = 100000; i < 300000; i++) {
			Random  random = new Random();
			int value = random.nextInt();
			hash.put("" +value, ""+value+" =----");
		}
		
		for (PhysicalNode node:nodes) {
			System.out.println(node.getNodeId()+":"+ node.size());
		}
		
	}

}
