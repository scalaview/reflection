import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import reflect.ClassHelper;
import entity.Course;
import entity.Student;
import factory.BeanFactory;

public class Main {

	/**
	 * @param args
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		BeanFactory beanFactory = new BeanFactory("src/conf.xml");
		Student stud0 = BeanFactory.getBean("stud0", Student.class);
		Course mathCourse = BeanFactory.getBean("mathCourse", Course.class);
		System.out.println("stud name:" + stud0.getName());
		System.out.println("stud NO.:" + stud0.getNO());
		Course testMathCourse = stud0.getCourse();
		System.out.println("the same ?:"
				+ (mathCourse == testMathCourse ? true : false));
		System.out.println("math name:" + mathCourse.getName());
		System.out.println("math credit:" + mathCourse.getCredit());
		System.out.println("math desc:" + mathCourse.getDesc());
		Map<String, Object> vals = ClassHelper.getMethods(stud0);
		for (Iterator<String> it = vals.keySet().iterator(); it.hasNext();) {
			String key=it.next();
			System.out.println(key+":"+vals.get(key));
		}
	}
}
