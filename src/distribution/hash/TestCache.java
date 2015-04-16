package distribution.hash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * ≤‚ ‘ª∫≥Â µœ÷
 * @author Administrator
 *
 */
public class TestCache implements CacheMap<String, Object>{
	
	private Map dataMap = new ConcurrentHashMap<String, Object>();
	
	@Override
	public Object get(String key) {
		return dataMap.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return dataMap.put(key, value);
	}

	@Override
	public int size() {
		return dataMap.size();
	}

}
