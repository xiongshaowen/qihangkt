package cn.ybzy.qihangkt.service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.ybzy.qihangkt.dao.BaseDao;
import cn.ybzy.qihangkt.tools.MapToEntityTool;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	/**
	 * 获取BaseDao对象的解决方案
	 * 不象HIBERNATE那样可以获取对象了（@autowired),因为此处的DAO层接口实现为xml文件格式，加入不了IOC容器
	 * 抽象类不能创建对象，只能在子类中继承实现,，接口也是只定义方法，不实现方法但接口可以创建对象（再上转对象即可）
	 * BaseDao接口由BaseDao.xml实现了
	 */
	public abstract BaseDao getBaseDao();

	private String tableName;
	public Class<?> clazz;

	/**
	 * this是调用该构造法的对象（一般在对表查询的时候用到,如UserServiceImpl.)getClass（）获取该对象的Class对象，getGenericSuperclass()获取它父类（BaseServiceImpl）的Class对象。
	 */
	public BaseServiceImpl() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<?>) types[0];
		// 获取表的名字 "t_"+"xxx"(t_user),toLowerCase把所大小转为小写，我们一定要把表的名字定义为t_模型类名的小写。
		tableName = "t_" + clazz.getSimpleName().toLowerCase(); // 注：这行代码写上Type...的上面会出现空指针异常，因为那时clazz没有初化
	}

	/**
	 * 这个添加方法，有局限性，只能是entity类的属性个数和表格的列数，他们是一个一对一的对应关系下才能使用
	 * 但是其后又因想，复杂查关联查询等，增加了字段，即表格字段与增加用户的字段不配匹。会发生异常
	 * 
	 * @param t
	 */
	@Override
	public void add(T t) {
		List<Object> list = new ArrayList<>();
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				list.add(field.get(t));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getBaseDao().add(tableName, list.toArray());
	}

	/*
	 * 这个增加方法，解决了上面的bug,
	 */
	@Override
	public void addForNotMatch(Object[] fieldNames, Object[] fieldVales) {
		getBaseDao().addForNotMatch(tableName, fieldNames, fieldVales);

	}

	@Override
	public void delete(int id) {
		getBaseDao().delete(tableName, id);

	}

	@Override
	public void update(T t) {
		List<Object> list = new ArrayList<>();
		int id = 0;
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);// 打开获取private修饰的属性权限
			try {
				if (field.get(t) == null) {
					continue;
				}
				if ("id".equals(field.getName())) {
					id = (int) field.get(t);
					continue;
				}
				// 剩下的字段，才是要修改到数据表里的字段值
				// 构造update中的set后面的 “字段名=修改后的字段值”
				list.add(field.getName() + "='" + field.get(t) + "'");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getBaseDao().update(tableName, id, list.toArray());
	}

	@Override
	public T select(int id) {
		Map<Object, Object> rsMap = getBaseDao().select(tableName, id);
		// 我们需要把Map转型T--Map里有很多个字段名：字段值的键值对
		@SuppressWarnings("unchecked")
		T t = (T) MapToEntityTool.map2entity(rsMap, clazz);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectAll() {
		List<Map<Object, Object>> rsList = getBaseDao().selectAll(tableName);
		List<T> list = new ArrayList<>();
		T t = null;
		for (Map<Object, Object> map : rsList) {
			// 需要我们把list集合里的map转变成T
			t = (T) MapToEntityTool.map2entity(map, clazz);
			list.add(t);
		}
		return list;
	}

}
