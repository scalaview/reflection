package xmlAnalysis;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import entity.Property;

public class XmlHelper {

	private Document doc;

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public static Map<String, Property> getBeanParams(Element bean) {
		Map<String, Property> map = new HashMap<String, Property>();
		for (Iterator<Element> beanIt = bean.elementIterator(); beanIt
				.hasNext();) {
			Element property = beanIt.next();
			Property p = new Property();
			p.setName(property.attribute("param").getStringValue());
			p.setRef(property.attribute("ref") == null ? false : true);
			p.setValue(property.getTextTrim());
			map.put(property.attribute("param").getStringValue(), p);
		}
		return map;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "src/conf.xml";
		Document doc = null;
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(new File(path));
			Element root = doc.getRootElement();
			for (Iterator<Element> beans = root.elementIterator(); beans
					.hasNext();) {
				Element bean = beans.next();
				map.put("name", bean.attributeValue("name"));
				// System.out.println(bean.elementText("property"));
				// System.out.println(bean.attribute("name").getText());
				for (Iterator<Element> beanIt = bean.elementIterator(); beanIt
						.hasNext();) {
					// System.out.println(beanIt.next().elementText("property"));
					Element property = beanIt.next();
					// property.getTextTrim();
					map.put(property.attribute("param").getStringValue(),
							property.getTextTrim());
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.getProperty("user.dir"));
	}

	public Document getDoc() {
		return doc;
	}
}
