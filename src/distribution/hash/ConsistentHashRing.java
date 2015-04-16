package distribution.hash;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * һ����hash�Ļ�
 * 1>��ʼ����ʱ��Ҫ����Ӧ��keyֵ�Ŀռ価������
 * 2>����key���Ի�ö�Ӧ���������
 */
public class ConsistentHashRing<T> {
	//hash����
	private final HashFunction hashFunction;
	//��Ӧһ���ڵ��ж��ٸ�����ڵ�
	private final int numberOfReplicas;
	//�����һ����
	private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

	public ConsistentHashRing(HashFunction hashFunction, int numberOfReplicas,
			Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;
		
		//�ڵ����ӵ�����
		for (T node : nodes) {
			add(node);
		}
		System.out.println("init nodes size is:" + circle.size());
	}
	

	public void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.put(hashFunction.hash(node.toString() + i), node);//hashֵ��node��ӳ��,hash��������Ҫ
		}
	}

	public void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString() + i));
		}
		System.out.println("after delete nodes size is:" + circle.size());
	}

	/**
	 *����key��ö�Ӧ��node�ڵ㣬����������ϵĽڵ�
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
			//���ص�ǰ��һ������hash��Sorted��map�ڵ�
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			//����map�е�һ����Ӧ�Ľڵ�
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		return circle.get(hash);
	}
}