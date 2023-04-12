package cn.ybzy.qihangkt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;

import cn.ybzy.qihangkt.model.Role;
import cn.ybzy.qihangkt.model.User;
import cn.ybzy.qihangkt.service.RoleService;
import cn.ybzy.qihangkt.service.UserService;
import cn.ybzy.qihangkt.web.AuthClass;
import cn.ybzy.qihangkt.web.AuthMethod;
@AuthClass   //这个注解就是标记，AdminController这个类是需要限才能访问的。
@Controller
//xiong熊少文
public class AdminController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	@AuthMethod   //这个注解就是标记，AdminController这个类的方法是需要限才能访问的。
	//@GetMapping("/admin/admin.html")           //权限控制中，这种写法不能获取/admin/admin.html当权限资源标志
	@RequestMapping(value="/admin/admin.html",method=RequestMethod.GET)
    public String admin() {
		
    	return "admin/admin";
    }
	
	/**
	 * 该方法功能：1.查询所有用户记录，保存到allRoles中，再让用户管理页user_manager.jsp接收再让它的弹出框-‘添加’之模态框用。
	 *            2.查询所有用户与关联记录分页，再显示在用户管理员中。
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@AuthMethod
	 //@GetMapping("/admin/user_manager.html")
	@RequestMapping(value="/admin/user_manager.html",method=RequestMethod.GET)
	public String userManager(Model model,Integer pageNum,Integer pageSize) {  //model是要注入map型对象的
		//获取到所有的角色记录信息，注入到User.jsp视图里
		List<Role> roles = roleService.selectAll();
		// System.out.println("roles:"+roles);   //测试：打开‘用户管理’后，可后台看到
		model.addAttribute("allRoles",roles);    //jsp页面可通过JSTL ${allRoles}接收，这有添加用户，关联角色中用到
		//List<User> users = userService.selectAll();  //该(共公方法）查询结果中不包括角色信息
		
		/*List<User> users = userService.selectRelevanceUsers();           //关联查询,查三个表，t_user,t_role,t_user_role
		model.addAttribute("allUsers",users);*/
		//分页
		//System.out.println("pageNum: "+pageNum+"-------pageSize:"+pageSize);
		if(pageSize==null || pageSize ==0) pageSize=10;
		if(pageNum==null || pageNum ==0) pageNum=1;              //解决，页面跳转显示该页时出现的空指针异常。
		PageInfo<User> users = userService.selectUsersByPager(pageNum,pageSize);  //该方法已调用了关联查询方法
		model.addAttribute("userDatsByPager",users);
		return "admin/user_manager";
	    
	}
	@AuthMethod
	 //@GetMapping("/admin/role_manager.html")
	@RequestMapping(value="/admin/role_manager.html",method=RequestMethod.GET)
	 public String roleManager() {
		 return "admin/role_manager";
		 
	 }
	@AuthMethod
	 //@GetMapping("/admin/resources_manager.html")
	@RequestMapping(value="/admin/resources_manager.html",method=RequestMethod.GET)
	 public String resourcesManager() {
		 return "admin/resources_manager";
		 
	 }
}
