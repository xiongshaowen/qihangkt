package cn.ybzy.qihangkt.model;

import java.util.List;

public class Role {
	private Integer id;
	private String name;
	private String code;
	private List<Resource> resources;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}


	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code + ", resources=" + resources + "]";
	}

	//重写判断两个对象是否相等的方法，因为有时复杂应用中，是从不同的内存中获取可能相同的对象--如：一个是从查询数据库后保存到List变量中获取的，一个是从自身关联中获取的，但是不能用Object中的方法去比较
	@Override
	public boolean equals(Object obj) {
	    //让判断两个role对象是不是相等的规则，我们要重写，id值一样，我们说两个role对相是相等的。
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
