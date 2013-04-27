package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassHelperReferance {
	 //��Ҫ�����JAVA����
	  private static Class reflectionClass;
	  private static String[][] relation;
	  private static String[] constructors;
	  private static String[] methods;
	  private static String[] fields;
	  
	  //������Ҫ�������
	  public static void load(String reflectionClassName) {
	    try {
	      reflectionClass = null;
	      reflectionClass = Class.forName(reflectionClassName);
	    } catch (ClassNotFoundException e) {
	      System.err.println(e.getMessage());
	    }
	  }
	  //��ȡ���ϵ
	  public static String[][] getRelation() {
	    String superClassName = reflectionClass.getSuperclass().getSimpleName();
	    Class[] interfaceClass = reflectionClass.getInterfaces();
	    if(superClassName == null)
	      superClassName = "Have not super class!";
	    if (reflectionClass.getInterfaces().length == 0) {
	      relation = new String[2][1];
	      relation[1][0] = "Have not interface!";
	    }else {
	      relation = new String[2][reflectionClass.getInterfaces().length];
	      for (int i = 0; i < interfaceClass.length; i++) {
	        relation[1][i] = interfaceClass[i].getName();
	        System.out.println( "interfaceClass:"+relation[1][i] );
	      }
	    }
	    relation[0][0] = superClassName;
	    System.out.println( "superClassName:"+superClassName );
	    return relation;
	  }
	 
	  //��ȡ���췽��
	  public static String[] getConstructors() {
	    Constructor[] constructor = reflectionClass.getDeclaredConstructors();
	    constructors = new String[constructor.length];
	    for (int i = 0; i < constructor.length; i++) {
	      Class[] paramType = constructor[i].getParameterTypes();
	      String param = "";
	      for (int j = 0; j < paramType.length; j++) {
	        param += paramType[j].getSimpleName().toString() + " ";
	      }
	      constructors[i] = constructor[i].getName().toString() 
	                + "(" + param + ")";
	      System.out.println("constructor:"+ constructors[i] );
	    }
	    return constructors;
	  }
	  //��ȡ�෽��
	  public static String[] getMethods() {
	    Method[] method = reflectionClass.getDeclaredMethods();
	    methods = new String[method.length];
	    for (int i = 0; i < method.length; i++) {
	      Class[] paramType = method[i].getParameterTypes();
	      String param = "";
	      for (int j = 0; j < paramType.length; j++) {
	        param += paramType[j].getSimpleName().toString() + " ";
	      }
	      methods[i] = method[i].getName().toString() 
	            + "(" + param + ")";
	      System.out.println("method:"+ methods[i]);
	    }
	    return methods;
	  }
	  //��ȡ������
	  public static String[] getFields() {
	    Field[] field = reflectionClass.getDeclaredFields();
	    fields = new String[field.length];
	    for (int i = 0; i < field.length; i++) {
	    field[i].setAccessible( true ); //�������˽������
	      fields[i] = field[i].toGenericString();
	      String type=field[i].getGenericType().toString();
	      System.out.println("fields:"+fields[i]+"   type:"+type);
	    }
	    return fields;
	  }
	  
	  //˽�з��������ⲿ����
	  private void testInvoke() {
	    System.out.println("invoke!");
	  }
	  private void testInvoke(String str) {
	    System.out.println("invoke!:" + str);
	  }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
