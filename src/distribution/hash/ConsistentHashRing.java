package distribution.hash;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash的环
 * 1>初始化的时候，要将对应的key值的空间尽量覆盖
 * 2>根据key可以获得对应的物理机器
 */
public class ConsistentHashRing<T> {
	//hash函数
	private final HashFunction hashFunction;
	//对应一个节点有多少个虚拟节点
	private final int numberOfReplicas;
	//排序的一个环
	private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

	public ConsistentHashRing(HashFunction hashFunction, int numberOfReplicas,
			Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;
		
		//节点增加到环中
		for (T node : nodes) {
			add(node);
		}
		System.out.println("init nodes size is:" + circle.size());
	}
	

	public void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.put(hashFunction.hash(node.toString() + i), node);//hash值到node的映射,hash函数很重要
		}
	}

	public void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString() + i));
		}
		System.out.println("after delete nodes size is:" + circle.size());
	}

	/**
	 *根据key获得对应的node节点，这个是物理上的节点
	 * 
	 * @param key
	 * @return
	 */
	public T get(Object key) {
		if (circle.isEmpty()) {
			return null;
		}
		int hash = hashFunction.hash(key);
		if (!circle.containsKey(hash)) {
			//返回当前第一个大于hash的Sorted的map节点
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			//返回map中第一个对应的节点
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		return circle.get(hash);
	}
}