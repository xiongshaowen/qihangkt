package cn.ybzy.qihangkt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ybzy.qihangkt.model.Resource;
import cn.ybzy.qihangkt.model.Role;
import cn.ybzy.qihangkt.model.User;
import cn.ybzy.qihangkt.service.UserService;
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
   /**
    * 登陆页面显示出来的方法
    */
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login.html",method=RequestMethod.POST)
	public String loginPost(String userInfo,String password,HttpSession session) {

		User user =userService.login(userInfo,password);
		session.setAttribute("loginUser", user);    //loginUser给权限拦截器AuthIncepertor使用
		//判断用记是不是超级管理员
		List<Role> loginUserRoles =user.getRoles();
		boolean isadmin = false; //是超级管理员：true,不是:false
		List<Resource> loginUserRes = null;
		List<String> loginUserPahtes = new ArrayList<String>();
		for(Role role:loginUserRoles) {
			if("admin".equals(role.getCode())) {
				isadmin = true;
				break;
			}
			//不是超级管理员的情况下，我们要把登录成功后的用户，关联的所有权限资源标记，取出来
			loginUserRes = role.getResources();
			for(Resource res:loginUserRes) {
				loginUserPahtes.add(res.getPath());  //存放了当前登陆用户所有的权限路径：如/admin/addUser.html
			}
		}
		session.setAttribute("isAdmin", isadmin);
		//循环完比后，loginUserPathes:包括了登录成功的用户，所拥有的所有权限标记
		session.setAttribute("loginUserAllPath", loginUserPahtes);
		return "redirect:/admin/admin.html";
	}
}
