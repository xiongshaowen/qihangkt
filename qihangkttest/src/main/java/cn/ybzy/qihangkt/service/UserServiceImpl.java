package cn.ybzy.qihangkt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ybzy.qihangkt.dao.BaseDao;
import cn.ybzy.qihangkt.dao.UserDao;
import cn.ybzy.qihangkt.dao.UserRoleDao;
import cn.ybzy.qihangkt.model.User;

@Service("userService") // 生成BaseServiceImpl代理对象userService放进IOC中，
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	@Autowired
	private UserDao userDao; // mybatis中的实现接口类(.xml)spring自动把之放到IOC中。
	@Autowired
	private UserRoleDao userRoleDao; // mybatis中的实现接口类(.xml)spring自动把之放到IOC中。

	@Override
	public BaseDao getBaseDao() {
		return userDao;
	}

	@Override
	public void addUser(User user, Integer[] roleIds) {
		// 1,先插入user然后，插入关联表

		/*
		 * //add(xx)方法有bug,只能是entity类的属性个数和表格的列数，他们是一个一对一的对应关系下才能使用 user.setEnable(1);
		 * //添加用户，默认为游活状态.1:激活。0：禁用 user.setAddDate(new Date()); this.add(user);
		 */

		this.addForNotMatch(new Object[] { "username", "password", "email", "phone", "enable", "add_date" },
				new Object[] { user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), 1,
						new Date()}); // 不同于hibernate，这里没法获直接取用户id,后面要把关联信息加到t_user_role表中，这要用到id
		// 根据新添加进去的用户的用户名称，查询出刚刚添加进数据库的用户记录，就需要一个方法，根氢用户获取对象,再获取id
		User u = userDao.getUserByName(user.getUsername());

		// 2,真正添加完成,添加到两个表中的数据
		if(roleIds==null||roleIds.toString().equals("")) roleIds=new Integer[]{3};//执行到这里也有空指针异常发生
		for (Integer rid : roleIds) {
			userRoleDao.add("t_user_role", new Object[] { null, u.getId(), rid });
			System.out.println(rid);
		}
	}

	// 关联查询
	@Override
	public List<User> selectRelevanceUsers() {

		return userDao.selectRelevanceUsers();
	}
    /**
     * 分页查询,把关联查询的结果集，再分页，以便在网页上分页显示
     */
	@Override
	public PageInfo<User> selectUsersByPager(int pageNum, int pageSize) {
		  Page<User> pager = PageHelper.startPage(pageNum,pageSize);
		  List<User> userDatas =userDao.selectRelevanceUsers();
		  PageInfo<User> info =new PageInfo<>(userDatas);
		  return info;
	}

	@Override
	public User getUserByUid(Integer uid) {
		User user =userDao.getUserByUid(uid);
		return user;
	}
    //修改用户
	@Override
	public void editUser(User user, Integer[] roleIds) {
		if("".equals(user.getPassword().trim())) user.setPassword(null);//如果表单提交的密码为空字符，则设置该对象的密码为null--由于BaseServiceImpl中定义了修改用户方法中，密为空，过滤没密码，保留原密码
		//修必用户的第一步：修改用户
		this.update(user);
		//修改用户第二步，是修改user关联的roles.
		//1.删除用户原有的关联role
		userRoleDao.deleteByUid(user.getId());
			
		//2.把接收到roleIds这里代指的关联roles重新加到用户角色关系表中 t_user_role.
		for(Integer rid:roleIds) 
			userRoleDao.add("t_user_role", new Object[] {null,user.getId(),rid});
		
	}
    //删除用户与及关联的对象
	@Override
	public void deleteByUidRelRole(Integer id) {
		//第一步：把该user的关联关系删除
		userRoleDao.deleteByUid(id);
		//第二上海：删除user本身
		this.delete(id);
		
	}
   //批量删除
	@Override
	public void batchDelUsersByIds(Integer[] uidIntArr) {
		for(Integer id:uidIntArr)
			this.deleteByUidRelRole(id);
		
	}
	//根据用户名或电话,模糊查询并分页
	@Override
	public PageInfo<User> selectUsersBySearchPage(int pageNum, int pageSize,String userInfo) {
		PageHelper.startPage(pageNum,pageSize);
		List<User> userDatas = userDao.selectUsersBySearchPage("%"+userInfo+"%");  //‘%’表示任意字符，任意个数
		PageInfo<User> info = new PageInfo<>(userDatas);
		return info;
	}
    //登陆判粘
	@Override
	public User login(String userInfo, String password) {
		//userInfo,查询user表里有没有响应的记录
		User user = userDao.selectUserByUserInfo(userInfo);
		//System.out.println("==================user====="+user);
		if(user == null) throw new RuntimeException("用户名或密码有误！");  //不能写太祥细了，模糊的告诉别人，省得容易破解
		if(!password.equals(user.getPassword()))
			throw new RuntimeException("用户名或密码有误！");
		return user;
	}
    
}
