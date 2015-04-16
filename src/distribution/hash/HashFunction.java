package distribution.hash;

/**
 *����Ӧ��keyӳ�䵽hash�ռ���
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
