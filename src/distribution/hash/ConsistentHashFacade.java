package distribution.hash;

import java.util.ArrayList;
import java.util.List;

/**
 *同步缓冲和数据库的代理实现类
 */
public class ConsistentHashFacade {
	private DbProxy  dbProxy = new DbProxy();
	private ConsistentHash  consistentHash = null;
	
	public ConsistentHashFacade(){
		
	}
	
	public void init(){
		List<PhysicalNode> nodes = new ArrayList<>();
		consistentHash = new ConsistentHash(nodes);
	}
	
	
	public Object get(String key){
		Object rObject = consistentHash.get(key);
		if (rObject == null) {
			rObject = dbProxy.get(key);
			consistentHash.put(key, rObject);
		}
		
		return rObject;
	}
	
	public void put(String key,Object iObject){
		dbProxy.put(key,iObject);
		consistentHash.put(key, iObject);
	}
}
