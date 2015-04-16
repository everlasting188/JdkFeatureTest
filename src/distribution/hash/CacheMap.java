package distribution.hash;

public interface CacheMap<T1, T2> {
	//插入数据
	public T2 get(T1 key);
	//获得数据
	public T2 put(T1 key, T2 value);
	
	//获得当前所有的数量
	public int size();
}
