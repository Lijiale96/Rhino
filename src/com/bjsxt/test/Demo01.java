package com.bjsxt.test;

import java.io.FileReader;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * ���Խű�����ִ��javascript����
 * @author A
 *
 */
public class Demo01 {
   public static void main(String[] args) throws Exception {
	
	 //��ýű��������
	   ScriptEngineManager sem = new ScriptEngineManager();
	   ScriptEngine engine = sem.getEngineByName("javascript");
	   
	 //����������洢��������������
	 engine.put("msg", "ljl is a good man");
     String str ="var user = {name:'ljl',age:18,schools:['���մ�ѧ','������ѧ��']};";
     str+="println(user.name);";

     //ִ�нű�
     engine.eval(str);
     engine.eval("msg='sxt is a good school';");
      System.out.println(engine.get("msg"));
      System.out.println("##############################");
	 
      //���庯��
      engine.eval("function add(a,b){var sum=a+b;return sum;}");
      //ȡ�ĵ��ýӿ�    
      Invocable jsInvoke =(Invocable) engine;
     //ִ�нű��ж���ķ���
      Object result1 = jsInvoke.invokeFunction("add", new Object[] {13,20});
      System.out.println(result1);
      
      //��������java����ʹ���������е�java��,����Ҫ�����˽�ϸ�ڣ�������ϸѧϰRhino���﷨
      String jsCode ="importPackage(java.util); var list=Arrays.asList(\"������ѧ��\",\"���մ�ѧ\",\"�Ͼ���ѧ\");";
      engine.eval(jsCode);
      
      engine.get("list");
      List<String> list2 = (List<String>) engine.get("list");
      for (String temp : list2) {
		System.out.println(temp);	
	}
     
      //ִ��һ��js �ļ������ǽ�a.js������Ŀ��src�¼��ɣ�
      URL url = Demo01.class.getClassLoader().getResource("a.js");
      FileReader fr = new FileReader(url.getPath());
      engine.eval(fr);
      fr.close();//����ֻ�ǲ��ԣ��Ͳ���ô�淶�ˡ���try catch finally��
      
}
}
