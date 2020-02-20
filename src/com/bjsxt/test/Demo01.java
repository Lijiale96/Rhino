package com.bjsxt.test;

import java.io.FileReader;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 测试脚本引擎执行javascript代码
 * @author A
 *
 */
public class Demo01 {
   public static void main(String[] args) throws Exception {
	
	 //获得脚本引擎对象
	   ScriptEngineManager sem = new ScriptEngineManager();
	   ScriptEngine engine = sem.getEngineByName("javascript");
	   
	 //定义变量，存储到引擎上下文中
	 engine.put("msg", "ljl is a good man");
     String str ="var user = {name:'ljl',age:18,schools:['江苏大学','江苏尚学堂']};";
     str+="println(user.name);";

     //执行脚本
     engine.eval(str);
     engine.eval("msg='sxt is a good school';");
      System.out.println(engine.get("msg"));
      System.out.println("##############################");
	 
      //定义函数
      engine.eval("function add(a,b){var sum=a+b;return sum;}");
      //取的调用接口    
      Invocable jsInvoke =(Invocable) engine;
     //执行脚本中定义的方法
      Object result1 = jsInvoke.invokeFunction("add", new Object[] {13,20});
      System.out.println(result1);
      
      //导入其他java包，使用其他包中的java类,若需要深入了解细节，可以详细学习Rhino的语法
      String jsCode ="importPackage(java.util); var list=Arrays.asList(\"北京尚学堂\",\"江苏大学\",\"南京大学\");";
      engine.eval(jsCode);
      
      engine.get("list");
      List<String> list2 = (List<String>) engine.get("list");
      for (String temp : list2) {
		System.out.println(temp);	
	}
     
      //执行一个js 文件（我们将a.js至于项目的src下即可）
      URL url = Demo01.class.getClassLoader().getResource("a.js");
      FileReader fr = new FileReader(url.getPath());
      engine.eval(fr);
      fr.close();//由于只是测试，就不那么规范了。用try catch finally！
      
}
}
