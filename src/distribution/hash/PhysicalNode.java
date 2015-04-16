package distribution.hash;

/**
 * ÿ���ڵ��϶����Խ��ж�Ӧ�Ĳ�ѯ����ʵ�ʵĽڵ������
 */
public class PhysicalNode {
	//�ڵ�����
	private int nodeId;
	

	//��Ӧ�����ݻ�ȡ����
	private CacheMap<String,Object> map ;
	
	//��ʼ��
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
	
	//�������
	public Object get(String key) {
		return map.get(key);
	}
	
	//��������
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
