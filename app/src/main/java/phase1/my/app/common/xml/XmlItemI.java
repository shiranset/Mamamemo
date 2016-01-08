package phase1.my.app.common.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Owner on 11/10/2014.
 */
public interface XmlItemI<T> {

    public Element toXml(T item, Document doc);
    public T fromXml(Element item);
    public String getElemName();
}
