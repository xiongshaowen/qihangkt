package cn.ybzy.qihangkt.java.map;

import org.aspectj.bridge.MessageWriter;

import cn.ybzy.qihangkt.dao.BaseDao;

public abstract class Test {
	public  abstract BaseDao getBaseDao();
	public String tableName;
	public static void main(String[] args) {
        Dog dog = new Dog();
        dog.say();    //子类继承调用Animal的普通成员方法
        dog.bark();    //子类调用已经实现过的方法
        
      }
	public void xiong() {
		//getBaseDao().add(tableName,"fdsa");
	}
}
