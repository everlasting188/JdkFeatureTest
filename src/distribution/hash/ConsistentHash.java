package distribution.hash;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *一致性hash的简单实现
 */
public class ConsistentHash implements Map<String, Object>{
	//hashRing
	private ConsistentHashRing<PhysicalNode> hashRing;
	private HashFunction hashFunction;
	
	public ConsistentHash(List<PhysicalNode> nodes){
		hashFunction = new HashFunction();
		hashRing = new ConsistentHashRing<>(hashFunction, 5, nodes);
	}
	
	
	/**
	 * 删除对应的节点
	 * @param node
	 */
	public void removeNode(PhysicalNode node){
		//移除对应的对象，然后如果有数据需要重新缓冲的话，会放置在下一个节点，不命中后引起数据缓冲的迁移
		hashRing.remove(node);
	}
	

	/**
	 * 增加对应的节点
	 * @param node
	 */
	public void addNode(PhysicalNode node){
		/**
		 * 增加节点，后续对应的数据会分布到这个Node上
		 * 1)对于一些key会因为增加后不命中，需要从数据库中填充
		 * 2）如果增加以后重新删除，会引起一些弱的不一致性
		 */
		hashRing.add(node);
	}
	
	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	@Override
	public Object get(Object key) {
		PhysicalNode node = hashRing.get(key);
		return node.get((String)key);
	}

	@Override
	public Object put(String key, Object value) {
		PhysicalNode node = hashRing.get(key);
		return node.put((String)key,value);
	}

	@Override
	public Object remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		
	}

	@Override
	public void clear() {
		
	}

	@Override
	public Set<String> keySet() {
		return null;
	}

	@Override
	public Collection<Object> values() {
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return null;
	}

}
