package cn.ybzy.qihangkt.java.map;

public class Dog extends Animal{
	 public void bark() {        //子类实现Animal的抽象方法
         System.out.println("汪汪~汪汪~");  
         System.out.println("我是子类，不能直接调用父类的私有变量a  ：（");  
         System.out.println("我是子类，只有通过super.getA()调用父类的私有变量a：" + super.getA());  
       }
}
