package factory;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import reflect.ClassHelper;
import xmlAnalysis.XmlHelper;
import entity.Property;

public class BeanFactory {

	/**
	 * @param args
	 */
	private String path;
	public static Map<String, Object> map;

	public BeanFactory() {
		this("src/bean.xml");
	}

	public BeanFactory(String path) {
		super();
		this.path = path;
		init();
	}

	private void init() {
		Document doc = null;
		map = new HashMap<String, Object>();
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(new File(path));
			Element root = doc.getRootElement();
			for (Iterator<Element> beans = root.elementIterator(); beans
					.hasNext();) {
				Element bean = beans.next();
				Map<String, Property> beanParams = XmlHelper.getBeanParams(bean);
				Class cla = Class.forName(bean.attributeValue("class"));
				Object obj = cla.newInstance();
				// set value
				ClassHelper.setFields(obj, beanParams, map);
				// ClassHelper.setMethods(obj, beanParams, map);
				map.put(bean.attributeValue("id"), obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(System.getProperty("user.dir"));
	}

	public static <T> T getBean(String key, Class<T> classOf) {
		return (T) map.get(key);
	}

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// Document doc = null;
	// Map<String, Object> map = new HashMap<String, Object>();
	// SAXReader reader = new SAXReader();
	// try {
	// doc = reader.read(new File(path));
	// Element root = doc.getRootElement();
	// for (Iterator<Element> beans = root.elementIterator(); beans
	// .hasNext();) {
	// Element bean = beans.next();
	// Map<String, String> beanParams = XmlHelper.getBeanParams(bean);
	// Class cla = Class.forName(bean.attributeValue("class"));
	// Object obj = cla.newInstance();
	// // set val
	// ClassHelper.setFields(obj, beanParams, map);
	// map.put(bean.attributeValue("name"), obj);
	// }
	// } catch (DocumentException | ClassNotFoundException
	// | InstantiationException | IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println(System.getProperty("user.dir"));
	// }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
