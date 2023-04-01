package cn.ybzy.qihangkt.java.map;

public abstract class Animal {
	private int a =10;
    public abstract void bark();
    
    public void say() {
    	System.out.println("我是抽象类中的非抽象方法，此抽象类中的私有成员变量a= \" + a");
    }
    public int getA() {
    	return a;
    }
    public void setA(int a) {
    	this.a=a;
    }
}
