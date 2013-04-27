package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import entity.Property;

public class ClassHelper {

	// 通过类方法获取属性值
	public static Map<String, Object> getMethods(Object obj)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		Method[] method = obj.getClass().getDeclaredMethods();
		for (int i = 0; i < method.length; i++) {
			String methodName = method[i].getName().toString();
			if (methodName.startsWith("get")) {
				map.put(method[i].getName().toString(),
						method[i].invoke(obj, null));
			}
		}
		return map;
	}

	// 通过类方法对属性赋值
	public static String[] setMethods(Object obj,
			Map<String, Property> propertyMap, Map<String, Object> objMap)
			throws NumberFormatException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method[] method = obj.getClass().getDeclaredMethods();
		String[] methods = new String[method.length];
		for (int i = 0; i < method.length; i++) {
			String methodName = method[i].getName().toString();
			if (methodName.startsWith("set")) {
				for (Class<?> c : method[i].getParameterTypes()) {
					String paramType = c.getSimpleName().toString();// 获取方法中变量的类型
					String name = method[i].getName().toString()
							.substring(3, methodName.length()).toLowerCase();// 记得要转换为小写，截取后得到的string开头字母是大写
					// System.out.println(name);
					Property property = propertyMap.get(name);
					String val = property.getValue();
					if (property.isRef()) {
						Object o = objMap.get(val);
						method[i].invoke(obj, o);
					} else if (paramType.toLowerCase().equals("long")) {
						method[i].invoke(obj, Long.parseLong(val));
					} else if (paramType.toLowerCase().equals("int")
							|| paramType.equals("Integer")) {
						method[i].invoke(obj, Integer.valueOf(val));
					} else if (paramType.toLowerCase().equals("float")) {
						method[i].invoke(obj, Float.valueOf(val));
					} else if (paramType.toLowerCase().equals("short")) {
						method[i].invoke(obj, Short.valueOf(val));
					} else if (paramType.toLowerCase().equals("double")) {
						method[i].invoke(obj, Double.valueOf(val));
					} else if (paramType.toLowerCase().equals("char")) {
						method[i].invoke(obj, val.charAt(0));
					} else if (paramType.toLowerCase().equals("boolean")) {
						method[i].invoke(obj, Boolean.valueOf(val));
					} else if (paramType.toLowerCase().equals("byte")) {
						method[i].invoke(obj, val.getBytes()[0]);
					} else {
							method[i].invoke(obj, val);
					}
				}
			}

		}
		return methods;
	}

	// 获取类属性的值
	public static Map<String, Object> getFieldsValues(Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] field = obj.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true); // 允许访问私有属性
			map.put(field[i].getName(), field[i].get(obj));// 使用属性的名称作为key
															// ，属性的值作为value
		}
		return map;
	}

	// 通过属性直接赋值
	public static String[] setFields(Object obj,
			Map<String, Property> propertyMap, Map<String, Object> objMap)
			throws NumberFormatException, IllegalArgumentException,
			IllegalAccessException {
		Field[] field = obj.getClass().getDeclaredFields();
		String[] fields = new String[field.length];
		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true); // 允许访问私有属性
			String name = field[i].getName();
			fields[i] = name;
			String type = field[i].getType().getSimpleName();
			// System.out.println("name:" + name + "   type:" + type);
			Property property = propertyMap.get(name);
			String val = property.getValue();
			if (property.isRef()) {
				Object o = objMap.get(val);
				field[i].set(obj, o);
			} else if (type.toLowerCase().equals("long")) {
				field[i].setLong(obj, Long.parseLong(val));
			} else if (type.toLowerCase().equals("int")
					|| type.equals("Integer")) {
				field[i].setInt(obj, Integer.valueOf(val));
			} else if (type.toLowerCase().equals("float")) {
				field[i].setFloat(obj, Float.valueOf(val));
			} else if (type.toLowerCase().equals("short")) {
				field[i].setShort(obj, Short.valueOf(val));
			} else if (type.toLowerCase().equals("double")) {
				field[i].setDouble(obj, Double.valueOf(val));
			} else if (type.toLowerCase().equals("char")) {
				field[i].setChar(obj, val.charAt(0));
			} else if (type.toLowerCase().equals("boolean")) {
				field[i].setBoolean(obj, Boolean.valueOf(val));
			} else if (type.toLowerCase().equals("byte")) {
				field[i].setByte(obj, val.getBytes()[0]);
			} else {
				// System.out.println(val);
				field[i].set(obj, val);
			}
		}
		return fields;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
