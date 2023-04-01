package cn.ybzy.qihangkt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ybzy.qihangkt.model.User;

public interface UserDao extends BaseDao {
   //因为继承了BaseDao，所以此处不用写重复的方法
	//根据用户名称，获取用户对象
	public User getUserByName(@Param("username") String username);  //@Param("username")指定参名不能改变

	public List<User> selectRelevanceUsers();
	/**
	 * 通过id关联查询单个用户，不能用BaseDao.java的select（）,因为它不能关联查询
	 * @param id
	 * @return
	 */
	public User getUserByUid(Integer id);
	//根据用户名或电话,模糊查询并分页
	public List<User> selectUsersBySearchPage(@Param("userInfo") String userInfo);

	public User selectUserByUserInfo(@Param("userInfo") String userInfo);
}
