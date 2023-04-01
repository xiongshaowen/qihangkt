package cn.ybzy.qihangkt.service;

import java.util.List;

public interface BaseService<T> {
	//增删改查
		/**
		 * 这个添加方法，有局限性，只能是entity类的属性个数和表格的列数，他们是一个一对一的对应关系下才能使用
		 * 但是其后又因想，复杂查关联查询等，增加了字段，即表格字段与增加用户的字段不配匹。会发生异常
		 * @param t
		 */
		public void add(T t);
		/*
		 * 这个增加方法，解决了上面的bug,
		 */
		public void addForNotMatch(Object[] fieldNames,Object[] fieldVales);
		
		public void delete(int id);
		
		public void update(T t);
		
		public T select(int id);
		
		public List<T> selectAll();
}
