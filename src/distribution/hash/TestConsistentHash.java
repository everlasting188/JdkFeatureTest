package distribution.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试一致性hash的类
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

		for (int i = 0; i < 20; i++) {
			TestCache cacheMap = new TestCache();
			PhysicalNode node = new PhysicalNode(5*i, cacheMap);
			nodes.add(node);
		}

		ConsistentHash hash = new ConsistentHash(nodes);
		for (int i = 0; i < 100000; i++) {
			hash.put("" +i, ""+i+" =----");
		}
		
		System.out.println("============before remove data================");
		for (PhysicalNode node:nodes) {
			System.out.println(node.getNodeId()+":"+node.size());
		}
		
		System.out.println("============after remove data================");
		PhysicalNode deletNode = new PhysicalNode(5,null);
		for (int i = 100000; i < 200000; i++) {
			hash.put("" +i, ""+i+" =----");
		}
		hash.removeNode(deletNode);
		for (PhysicalNode node:nodes) {
			System.out.println(node.getNodeId()+":"+ node.size());
		}
		
	}

}
