package cn.ybzy.qihangkt.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.github.pagehelper.PageInfo;

import cn.ybzy.qihangkt.model.Role;
import cn.ybzy.qihangkt.model.User;
import cn.ybzy.qihangkt.service.RoleService;
import cn.ybzy.qihangkt.service.UserService;
import cn.ybzy.qihangkt.web.AuthClass;
import cn.ybzy.qihangkt.web.AuthMethod;
@AuthClass
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	// 添加用户。
	@AuthMethod // 这个注解就是标记，AdminController这个类的方法是需要限才能访问的。
	// @PostMapping("/admin/addUser.html") //权限控制中，这种写法不能获取/admin/admin.html当权限资源标志
	@RequestMapping(value = "/admin/addUser.html", method = RequestMethod.POST)
	public String addUser(User user, Integer[] roleIds) { // 添加用户时，因为角色-是又是一个对象，所以要另一个变量接收之
		// System.out.println("Users"+user);
		// System.out.println(roleIds); //这两个由于上面提交了，这里都可接收到，所以测试看看
		userService.addUser(user, roleIds);
		return "redirect:/admin/user_manager.html";
	}

	@AuthMethod
	@ResponseBody
	@RequestMapping(value = "/admin/editUser.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String editUser(Integer id, HttpServletRequest request) {
		System.out.println("测试id有没有传过来:" + id);
		User user = userService.getUserByUid(id);
		// 获取到所有的role对象的信息
		List<Role> allRoles = roleService.selectAll();
		List<Role> roles = user.getRoles();
		String optStr = " ";
		for (Role role : allRoles) {
			System.out.println(roles.contains(role));
			if (roles.contains(role)) {
				optStr = optStr + "<option selected value=\"" + role.getId() + "\">" + role.getName() + "</option>\r\n";
			} else {
				optStr = optStr + "<option value=\"" + role.getId() + "\">" + role.getName() + "</option>\r\n";
			}
		}
		String path = request.getContextPath();
		return "<div class=\"modal-header\">\r\n"
				+ "					<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n"
				+ "						<span>&times;</span>\r\n" + "					</button>\r\n"
				+ "					<h4 class=\"modal-title\" id=\"myModalLabel\">编辑用户</h4>\r\n"
				+ "				</div>\r\n" + "				<div class=\"modal-body\">\r\n"
				+ "					<form id=\"editUserForm\" action=\"" + path
				+ "/admin/updateUser.html\" method=\"post\">\r\n"
				+ "                       <input type='hidden' name='id' value='" + user.getId() + "'/>"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>用户名：</label> <input type=\"text\" disabled name=\"username\" class=\"form-control\" value=\""
				+ user.getUsername() + "\">\r\n" + "						</div>\r\n"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>密码(留空表示不修改密码：</label> <input type=\"password\" name=\"password\"  value=\""
				+ "\" class=\"form-control\">\r\n" + "						</div>\r\n"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>关联的角色：</label> \r\n"
				+ "						<select name=\"roleIds\" class=\"selectpicker form-control\" multiple>\r\n"
				+ optStr + "						</select>\r\n" + "						</div>\r\n"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>手机号：</label> <input type=\"text\" name=\"phone\" class=\"form-control\" value=\""
				+ user.getPhone() + "\">\r\n" + "						</div>\r\n"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>邮箱：</label> <input type=\"text\" name=\"email\" class=\"form-control\" value=\""
				+ user.getEmail() + "\">\r\n" + "						</div>\r\n" + "					</form>\r\n"
				+ "				</div>\r\n" + "				<div class=\"modal-footer\">\r\n"
				+ "					<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n"
				+ "					<button onclick=\"editUserFormSubmit()\" id=\"editUserBtn\" type=\"button\" class=\"btn btn-primary\">编辑用户</button>\r\n"
				+ "				</div>";
	}

	// 编辑用户
	@AuthMethod
	// @PostMapping(value="/admin/updateUser.html")
	@RequestMapping(value = "/admin/updateUser.html", method = RequestMethod.POST)
	public String updateUser(User user, Integer[] roleIds) {
		userService.editUser(user, roleIds); // editUser在BaseDao.java与BaseDao.xml也没定义，它定义的是update是简单对一个表的修改。我们要用的是关联修改
		return "redirect:/admin/user_manager.html";
	}

	// 删除用户记录
	@AuthMethod
	// @GetMapping(value="/admin/delUser.html")
	@RequestMapping(value = "/admin/delUser.html", method = RequestMethod.GET)
	public String delUserById(Integer id) {
		// userService.delete(id); //只删除没有关联到角色的用记记录

		userService.deleteByUidRelRole(id);
		return "redirect:/admin/user_manager.html";
	}

	// 批量删除用户
	@AuthMethod
	@ResponseBody // 响应JSON(success)字符给ajax
	// @PostMapping(value="/admin/batchDelUsers.html")
	@RequestMapping(value = "/admin/batchDelUsers.html", method = RequestMethod.POST)
	public String batchDelUsers(String uid) { // uid是json格式发送过来的，是字符串-'["1","2",....]'
		// 真和实现删除用记功能的代
		// System.out.println("uid======="+uid); //["1","2",....],这不是我们要的，我们要的是
		// 1，2，3，。。。整型数组
		uid = uid.substring(1, uid.length() - 1); // 去掉左右[],substring截取了从第二个字符到倒数第二个字符的字符串
		uid = uid.replaceAll("\"", ""); // 把""换掉，形成 1,2,3...字符串
		String[] uidStrArr = uid.split(","); // 把1，2，3...字符串转为字符串数组
		// System.out.println(Arrays.toString(uidStrArr)); //[1, 2, 4, 5, 9, 10, 11, 12,
		// 13, 14]
		Integer[] uidIntArr = new Integer[uidStrArr.length];
		for (int i = 0; i < uidStrArr.length; i++) {
			uidIntArr[i] = Integer.parseInt(uidStrArr[i]);
		}
		// System.out.println(uidIntArr); //[1, 2, 4, 5, 9, 10, 11, 12, 13, 14]
		userService.batchDelUsersByIds(uidIntArr);
		return "success";
	}

	// 糢糊查询,用户名
	@AuthMethod
	// @PostMapping(value="/admin/userSearch.html")
	@RequestMapping(value = "/admin/userSearch.html", method = RequestMethod.POST)
	public String searchUsers(Model model, String userInfo) {
		System.out.println("userinfo: " + userInfo);
		List<Role> roles = roleService.selectAll();
		model.addAttribute("allRoles", roles);
		int pageNum = 1;
		int pageSize = 10;

		PageInfo<User> users = userService.selectUsersBySearchPage(pageNum, pageSize, userInfo); // 该方法已调用了关联查询方法
		model.addAttribute("userDatsByPager", users);
		return "admin/user_manager";
	}

	//这个是测试用的，我们没有加权限控制
	@ResponseBody
	@GetMapping(/* value= */"/test") // 相当于前面学的 @RequestMapping(value="/test",method=RequestMethod.GET)
	// @PostMapping("/test") //相当于前面学的
	// @RequestMapping(value="/test",method=RequestMethod.POST)
	public List<User> testUser() {

		// 增加记录
		/*
		 * User user =new User(); user.setUsername("小白"); user.setPassword("123456");
		 * user.setEmail("65179334@qq.com"); user.setPhone("13065156391");
		 * user.setEnable(1); userService.add(user);
		 */
		// userService.delete(3);
		// 修改记录
		/*
		 * User user =new User(); user.setId(2); //注：修改时，要该ID已存在数据表中，不存在修改不了
		 * user.setUsername("小华"); user.setPassword("123456");
		 * user.setEmail("65179334@qq.com"); user.setPhone("6666666666");
		 * user.setEnable(0); userService.update(user); return "add success";
		 */
		// 查询记录
		List<User> users = userService.selectAll();
		return users;

	}

}
