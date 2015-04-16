package distribution.hash;

/**
 *将对应的key映射到hash空间上
 */
public class HashFunction {

	public Integer hash(String key) {
		return MurmurHash.hash32(key);
	}

	public int hash(Object key) {
		String strKey = (String)key;
		return MurmurHash.hash32(strKey);
	}

}
