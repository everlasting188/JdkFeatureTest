package collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 *
 */
public class MapTest {

	public static void main(String[] args) {
		//testHashMap();
		testHashtable();
	}
	
	/**
	 * Hashtable相关类的处理
	 */
	public static void testHashtable(){
		Hashtable<Integer, String> map = new Hashtable<Integer, String>();
		//init
        for(int i = 0; i < 10; i++){
            map.put(i, "value" + i);
        }
 
        for(Map.Entry<Integer, String> entry : map.entrySet()){
             Integer key = entry.getKey();
             if(key % 2 == 0){
             System.out.println("To delete key " + key);
                 map.remove(key);
                 System.out.println("The key " + + key + " was deleted");
             }
        }
 
        System.out.println("map size = " + map.size());
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            System.out.println( entry.getKey() +" = " + entry.getValue());
        }
	}

	/**
	 * http://dumbee.net/archives/41
	 * 测试hashMap的迭代处理
	 */
	public static void testHashMap(){
		HashMap<String, Object> tHashtable = new HashMap<String, Object>();
		for (int i = 0; i < 100; i++) {
			tHashtable.put("" + i, "" + i);
		}
		
		/**
		Iterator<String> enumration = tHashtable.keySet().iterator();
		int pos = 0;
		while (enumration.hasNext()) {
			String key = (String) enumration.next();
			if (pos == 10) {
				tHashtable.remove(key);
			}
			pos++;
		}
		System.out.println("hello world!!");
		*/

		
		//直接使用内部迭代器进行删除
		Iterator<Map.Entry<String, Object>> it = tHashtable.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			Integer key = Integer.parseInt(entry.getKey());
			if (key % 30 == 0) {
				System.out.println("To delete key " + key);
				it.remove();
				System.out.println("The key " + +key + " was deleted");
			}
		}
	}
}
