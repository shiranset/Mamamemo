package phase1.my.app.common.xml;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Owner on 14/10/2014.
 */
public class XmlUtil {

    public static final XmlCalendarBuilder XML_CALENDAR_BUILDER = new XmlCalendarBuilder();

    public static Element getFirstElementsByTagName(Element parent, String tag) {
        NodeList list  = parent.getElementsByTagName(tag);
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                return (Element)node;
            }
        }
        return null;
    }

    public static Integer getIntegerAttribute(Element element, String tag, Integer defaultValue) {
        try {
            String val = element.getAttribute(tag);
            return val != null ? Integer.parseInt(val) : defaultValue;
        }catch (Exception e) {
            return defaultValue;
        }
    }

    public static String getStringAttribute(Element element, String tag, String defaultValue) {
        try {
            return element.getAttribute(tag);
        }catch (Exception e) {
            return defaultValue;
        }
    }


    public static void setIntAttribute(Element element, String tag, int value) {
        element.setAttribute(tag,Integer.toString(value));
    }

    public static int getIntAttribute(Element element, String tag, int defaultValue) {
        int num = defaultValue;
        String val = null;
        try {
            val = element.getAttribute(tag);
            num = Integer.parseInt(val);
        }catch (Exception e) {
            Log.e("XML","Failed to parse int for tag = " + tag  + " val = " + val, e );
        }
        return num;
    }


    public static class XmlCalendarBuilder implements XmlItemI<Calendar> {

        private static final String XML_ELEM_CALENDAR = "CALENDAR";
        private static final String XML_ATTR_YEAR = "YEAR";
        private static final String XML_ATTR_MONTH = "MONTH";
        private static final String XML_ATTR_DAYOFMONTH = "DAYOFMONTH";

        @Override
        public Element toXml(Calendar cal, Document doc) {
            Element root = doc.createElement(XML_ELEM_CALENDAR);
            XmlUtil.setIntAttribute(root, XML_ATTR_YEAR, cal.get(Calendar.YEAR));
            XmlUtil.setIntAttribute(root, XML_ATTR_MONTH, cal.get(Calendar.MONTH));
            XmlUtil.setIntAttribute(root, XML_ATTR_DAYOFMONTH, cal.get(Calendar.DAY_OF_MONTH));
            return root;
        }

        @Override
        public Calendar fromXml(Element element) {
            Calendar cal = Calendar.getInstance();
            int year = XmlUtil.getIntAttribute(element, XML_ATTR_YEAR, cal.get(Calendar.YEAR));
            int month = XmlUtil.getIntAttribute(element, XML_ATTR_MONTH, cal.get(Calendar.MONTH));
            int dayOfMonth = XmlUtil.getIntAttribute(element,XML_ATTR_DAYOFMONTH, cal.get(Calendar.DAY_OF_MONTH));
            cal.set(year, month, dayOfMonth);
            return cal;
        }

        @Override
        public String getElemName() {
            return XML_ELEM_CALENDAR;
        }
    }
}
