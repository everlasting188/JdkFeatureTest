package distribution.hash;

public interface CacheMap<T1, T2> {
	//��������
	public T2 get(T1 key);
	//�������
	public T2 put(T1 key, T2 value);
	
	//��õ�ǰ���е�����
	public int size();
}
