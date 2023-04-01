package cn.ybzy.qihangkt.model;

import java.util.List;
import java.util.Date;

/**
 * 注意：先建表t_user（要这样写，因为在工具类或DAO,SERVICE层已经有这样的要求）
 *      再对应表的字段，定义User类的成变员变量
 * @author Administrator
 *
 */
public class User {
     //private int id;   //这里有一个坑 ,在tools的MapToEntityTool.java中：set方法中的参数类型：int======数据库中查询的结果集中数据类型：class java.lang.Integer
    private Integer id; 
	private String username;
     private String password;
     private String email;
     private String phone;
     private Integer enable;   //0表示未激活，1表示激法了
     private Date addDate;
     private List<Role> roles;
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", enable=" + enable + ", addDate=" + addDate + ", roles=" + roles + "]";
	}
	
     
}
