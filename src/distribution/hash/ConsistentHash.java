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
		hashRing.remove(node);
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
