package phase1.my.app.common.list;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;

import phase1.my.app.common.xml.XmlItemI;
import phase1.my.app.common.xml.XmlUtil;

/**
 * Created by Owner on 07/12/2014.
 */
public class ListItem<E extends Serializable>  implements Serializable {

    private static Integer LAST_KEY  = 0;
    private final E item;

    private final Integer key;

    protected ListItem(E item, Integer key) {
        this.item = item;
        this.key = key == null ? LAST_KEY + 1 : key;
        if (this.key > LAST_KEY) {
            LAST_KEY = this.key;
        }
    }

    public E getItem() {
        return item;
    }

    public Integer getKey() {
        return key;
    }

    protected static abstract class XmlBuilder<T extends ListItem<K>, K extends Serializable> implements XmlItemI<T> {

        private static final String XML_ATTR_KEY = "KEY";
        private final  XmlItemI<K> xmlItemI;

        public XmlBuilder(XmlItemI<K> xmlItemI) {
            this.xmlItemI = xmlItemI;
        }

        @Override
        public Element toXml(T p, Document doc) {
            Element root = doc.createElement(getElemName());
            root.setAttribute(XML_ATTR_KEY, p.getKey().toString());
            root.appendChild(xmlItemI.toXml(p.getItem(), doc));
            return root;
        }

        @Override
        public T fromXml(Element element) {
            Element personElem = XmlUtil.getFirstElementsByTagName(element, xmlItemI.getElemName());
            K item= xmlItemI.fromXml(personElem);
            Integer key = XmlUtil.getIntegerAttribute(element, XML_ATTR_KEY, null);
            return createListItem(key, item);
        }


        public abstract T createListItem(Integer key, K item);
    }
}
