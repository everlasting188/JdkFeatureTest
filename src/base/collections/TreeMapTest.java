package base.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * TreeMap¿ÉÒÔÅÅĞò
 */
public class TreeMapTest {

	public static void main(String[] args) {
		Map<String, String>   treeMap = new TreeMap<>();
		
		treeMap.put("1112", "1112_1");
		treeMap.put("111", "111_1");
		treeMap.put("333", "333_1");
		
		Set keySets = treeMap.keySet();
		Iterator  iterator = keySets.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println("key is:" + key);
		}
		
		
		Map<String, String>   treeMap2 = new HashMap<>();
		treeMap2.put("1112", "1112_1");
		treeMap2.put("111", "111_1");
		treeMap2.put("333", "333_1");
		keySets = treeMap2.keySet();
		iterator = keySets.iterator();
		System.out.println("==============");
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println("key is:" + key);
		}
	}

}
