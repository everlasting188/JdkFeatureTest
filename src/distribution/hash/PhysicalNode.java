package distribution.hash;

/**
 * 每个节点上都可以进行对应的查询，有实际的节点来完成
 */
public class PhysicalNode {
	//节点名称
	private int nodeId;
	

	//对应的数据获取对象
	private CacheMap<String,Object> map ;
	
	//初始化
	public PhysicalNode(int id,CacheMap<String,Object> inMap){
		nodeId = id;
		map = inMap;
	}
	
	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
	public int size(){
		return map.size();
	}
	
	//获得数据
	public Object get(String key) {
		return map.get(key);
	}
	
	//放入数据
	public Object put(String key, Object value) {
		return  map.put(key,value);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return ""+nodeId;
	}
	
	
}
