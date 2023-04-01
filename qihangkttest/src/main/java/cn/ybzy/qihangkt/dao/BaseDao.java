package cn.ybzy.qihangkt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 不像SSH整合那样，由于BaseDaor接口是由开发人员自定义实现的，从而要用到泛型，
    public T add(T t);
    public void delete(int id);
 * 但
 * mybatis中，BaseDao接口由其内部自动创建 动态代理来实现接口所以我们可以不用泛型类
 * 
 *
 */
public interface BaseDao {
	//增删改查
		public void add(@Param("tableName") String tableName,@Param("objects") Object[] objects);  //Object... objects
		
		public void delete(@Param("tableName") String tableName,@Param("id") Integer id);
		
		public void update(@Param("tableName") String tableName,@Param("id") Integer id,@Param("objects") Object[] objects);

		public Map<Object, Object> select(@Param("tableName") String tableName,@Param("id") Integer id);
		
		public List<Map<Object, Object>> selectAll(@Param("tableName") String tableName);

		public void addForNotMatch(@Param("tableName") String tableName,@Param("fieldNames") Object[] fieldNames,@Param("fieldVales") Object[] fieldVales);
}
