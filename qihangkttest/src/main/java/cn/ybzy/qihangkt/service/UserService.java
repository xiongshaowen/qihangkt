package cn.ybzy.qihangkt.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.ybzy.qihangkt.model.User;

public interface UserService extends BaseService<User>{

	void addUser(User user, Integer[] roleIds);
    /**
     * 获取所有的用户记录信息，实现关联查询
     * @return
     */
	List<User> selectRelevanceUsers();
    public PageInfo<User> selectUsersByPager(int pageNum,int pageSize);
    
    public User getUserByUid(Integer uid);
    /**
     * 修改用户
     * @param user
     * @param roleIds
     */
	void editUser(User user, Integer[] roleIds);
	/**
	 * 删除用户与关联表信息
	 * @param id
	 */
	void deleteByUidRelRole(Integer id);
	//批量删除用户记录
	void batchDelUsersByIds(Integer[] uidIntArr);
	//根据用户名或电话,模糊查询并分页
	PageInfo<User> selectUsersBySearchPage(int pageNum, int pageSize, String userInfo);
	
	User login(String userInfo, String password);
}
