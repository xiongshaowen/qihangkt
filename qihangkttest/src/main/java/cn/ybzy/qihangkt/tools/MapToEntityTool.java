package cn.ybzy.qihangkt.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.RenderingHints.Key;
import java.lang.reflect.*;

public class MapToEntityTool {
	/**
	 * 缓存类的属性信息
	 */
	private static Map<String, EntityCacheItem> convertItemCache = new HashMap<>();

	/**
	 * map to entry 的泛型方法 功能：把map对象转化为entity实体对象（entityClass对应的）
	 * 原理：首先获取参数entityClass的所有字段，与方法，所有字段名放在fieldNameList集合中,所有方法放在Map集合中
	 * 其次：获取mybatis传递过来的Map集合中的所有键值（键名与entityClass传递过来的字段名一样，不一样不做处理） 所有发生的异常，return
	 * null;这样不留垃圾
	 * 
	 * @return
	 */
	public static <T> T map2entity(Map<Object, Object> map, Class<T> entityClass) {
		EntityCacheItem entityCacheItem = convertItemCache.get(entityClass.getName());
		if(entityCacheItem ==null) {
			entityCacheItem = EntityCacheItem.createEntityCacheItem(entityClass);
			convertItemCache.put(entityClass.getName(),entityCacheItem);
		}
		//entityClass拿到参数传来的对象的属性名秒称（List）集合
		List<String> fieldNameList = entityCacheItem.getFieldNameList();
		//通过entityClass参数，获取类型里边的set方法的map集合
		Map<String,Method> setMethodMap=entityCacheItem.getSetMethodMap();
		System.out.println("数据库中查询的结果集："+map);
		System.out.println("实体类对象中的属性名字："+fieldNameList);
		Map<Object, Object> targetMap = new HashMap<>();
		String key;
		String key1;
		String key2;
		for(Map.Entry<Object, Object> entry:map.entrySet()) {
			key = entry.getKey().toString();
			while(key.contains("_")) {
				//add_date  a_b_c_d
				key1 = key.substring(0,key.indexOf("_")); //add
				key2 = key.substring(key.indexOf("_")+1); //date
				key = key1 + key2.substring(0,1).toUpperCase()+key2.substring(1); //addDate
			}
			targetMap.put(key, entry.getValue());
		}
		T entity = null;
		try {
			entity = entityClass.newInstance(); // 反射方式创建一个对象（实例entity),此时它还是空的，下面要注入内容
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} // 通过反射方式，获取这个类型的对象

		Object mapFieldValue = null;
		Method setMethod1 = null;
		Class<?>[] parameterTypes = null;
		for (String fieldName1 : fieldNameList) { // 循环拿到Map集合（调用时参数传过来的map)中的键:循环把map所有的键值注入到实体中（如:User会对应很多setXXX设置字段的方法）
			mapFieldValue = targetMap.get(fieldName1);
			if (mapFieldValue == null)
				continue; // 如果键不存在，就没有做本次循环的必要，继续进行一下次循环
			setMethod1 = setMethodMap.get(fieldName1); // 如果方法不存在，也没有。。。。
			if (setMethod1 == null)
				continue;
			parameterTypes = setMethod1.getParameterTypes(); // 获取方法中的所有类型的参数数组
			if (parameterTypes == null || parameterTypes.length > 1) { // 如果setxxx方法中参数没有或大于1个时也不做本次循环，因为setUsername(xx)中只有一个参数
				continue;
			}
			if (parameterTypes[0].isAssignableFrom(mapFieldValue.getClass())) {
				// 若map传来的属性值的类型和set方法中参数的类型一致
				// 如：setUsername(Object)的object,与mapFieldValue的类型是否一致
				try {
					setMethod1.invoke(entity, mapFieldValue);// 调用是对象的set方法把属性值注入到实体里（entity--不是键值对的)
																// 如：setUsername("xiongshaowen");
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				//这里输出让我们看到，封装时封装的数据类型，基本数据类型为它的包装类，特别是模型类中int id;要定为Integer id;
				System.out.println(
						"不同类型：set方法中的参数类型：" + parameterTypes[0] + "======数据库中查询的结果集中数据类型：" + mapFieldValue.getClass());
			}
		}

		return entity;
	}
	// 定义一个缓存（静态存储）内部类，实例化后有把传来的类封装字段和方法到List,Map集合中的功能。把map转换实例时，会非常频繁的造访下面代码，这样我们定义一个缓存减少系统消耗
		static class EntityCacheItem {
			private EntityCacheItem() {
			}; // 私有化构造（无参）方法，让该类不可在外部实例化，要通过内部方法来创建实例对象

			private List<String> fieldNameList = new ArrayList<String>();
			private Map<String, Method> setMethodMap = new HashMap<>();

			public List<String> getFieldNameList() {
				return fieldNameList;
			}

			public Map<String, Method> getSetMethodMap() {
				return setMethodMap;
			}

			public void parseEntity(Class<?> entityClass) {
				Field[] fields = entityClass.getDeclaredFields(); // 获取所有字段，不管私有还是公有
				String fieldName;
				String setMethodName;
				Method setMethod = null;
				setMethodMap = new HashMap<>();
				for (Field field : fields) {
					field.setAccessible(true); // 获取可修改字段的权限
					fieldName = field.getName(); // 属性字段的名字
					fieldNameList.add(fieldName);
					setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); // 设置类似javabean方法，如：setUsername(Username)
					try {
						setMethod = entityClass.getDeclaredMethod(setMethodName, field.getType()); // 拿到实体对应的类中的setUsername(Object
																									// param)方法
					} catch (Exception e) {
						e.printStackTrace();

					}
					setMethodMap.put(fieldName, setMethod); // 如：Map集合中的一个元素{username,setUsername(Object param)}
				}
			}

			// 通过内部创建该类实例对象，
			public static EntityCacheItem createEntityCacheItem(Class<?> entityClass) {
				EntityCacheItem ci = new EntityCacheItem();
				ci.parseEntity(entityClass);
				return ci;
			}
		}

	/**
	 * map to entry 的泛型方法 功能：把map对象转化为entity实体对象（entityClass对应的）
	 * 原理：首先获取参数entityClass的所有字段，与方法，所有字段名放在fieldNameList集合中,所有方法放在Map集合中
	 * 其次：获取mybatis传递过来的Map集合中的所有键值（键名与entityClass传递过来的字段名一样，不一样不做处理） 所有发生的异常，return
	 * null;这样不留垃圾
	 * 
	 * @return
	 *//*
	    //下面注释的代码，实现功能与上面代码功能一样.
		  public static <T> T map2entity(Map<Object,Object> map,Class<T> entityClass) {
    	//entityClass拿到参数传来的对象的属性名秒称（List）集合
    	//通过entityClass参数，获取类型里边的set方法的map集合
    	Field[] fields = entityClass.getDeclaredFields();  //获取所有字段，不管私有还是公有
    	List<String> fieldNameList = new ArrayList<String>();
    	String fieldName;
    	String setMethodName;
    	Method setMethod = null;
    	Map<String,Method> setMethodMap = new HashMap<>();
    	for(Field field:fields) {
    		field.setAccessible(true);  //获取可修改字段的权限
    		fieldName =field.getName();             //属性字段的名字
    		fieldNameList.add(fieldName);
    		setMethodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);  //设置类似javabean方法，如：setUsername(Username)
    		try {
				setMethod= entityClass.getDeclaredMethod(setMethodName,field.getType());  //拿到实体对应的类中的setUsername(Object param)方法
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
    		setMethodMap.put(fieldName, setMethod);   //如：Map集合中的一个元素{username,setUsername(Object param)}
    	}
    	
    	T entity = null;
    	try {
			entity = entityClass.newInstance();     //反射方式创建一个对象（实例entity),此时它还是空的，下面要注入内容
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}   //通过反射方式，获取这个类型的对象
    	
    	Object mapFieldValue=null;
		Method setMethod1 = null;
		Class<?>[] parameterTypes=null;
    	for(String fieldName1:fieldNameList) {  //循环拿到Map集合（调用时参数传过来的map)中的键:循环把map所有的键值注入到实体中（如:User会对应很多setXXX设置字段的方法）
    		mapFieldValue = map.get(fieldName1);
    		if(mapFieldValue == null) continue;   //如果键不存在，就没有做本次循环的必要，继续进行一下次循环
    		setMethod1 = setMethodMap.get(fieldName1);  //如果方法不存在，也没有。。。。
    		if(setMethod1 ==null) continue;
    		parameterTypes = setMethod1.getParameterTypes();  //获取方法中的所有类型的参数数组
    		if(parameterTypes ==null || parameterTypes.length>1) {  //如果setxxx方法中参数没有或大于1个时也不做本次循环，因为setUsername(xx)中只有一个参数
    			continue;
    		}
    		if(parameterTypes[0].isAssignableFrom(mapFieldValue.getClass())) {
				//若map传来的属性值的类型和set方法中参数的类型一致 如：setUsername(Object)的object,与mapFieldValue的类型是否一致
				try {
					setMethod1.invoke(entity, mapFieldValue);//调用是对象的set方法把属性值注入到实体里（entity--不是键值对的) 如：setUsername("xiongshaowen");
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}  
			}else {
				System.out.println("不同类型：set方法中的参数类型：" + parameterTypes[0] + "======数据库中查询的结果集中数据类型：" + mapFieldValue.getClass());
			}
		}
    		
		return entity;
    }
		 */

}
