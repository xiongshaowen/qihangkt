package cn.ybzy.qihangkt.java.map;

import static org.hamcrest.CoreMatchers.containsString;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapDemo {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		/*map.put("星期一", "Monday");
		map.put("星期日", "Sunday");
		map.put("星期二", "Tusday");
		System.out.println(map);
		System.out.println(map.get("星期一"));
		//String value = map.remove("星期日");
		System.out.println(map);
		//System.out.println(value);
		
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()) {
			String key =iterator.next();
			String value1 = map.get(key);
			System.out.println(key+"="+value1);
		}*/
		
		map.put("邓超", "孙丽");
		map.put("赵微", "黄有龙");
		map.put("刘德华", "柳岩");
		Set<Map.Entry<String,String>> entrySet = map.entrySet();
		Iterator<Map.Entry<String,String>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Map.Entry<String,String> entry =iterator.next();
			String key = entry.getKey();
			String value= entry.getValue();
			System.out.println(key+"="+value);
		}
	}

}
