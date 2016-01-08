package phase1.my.app.common.xml;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Owner on 11/10/2014.
 */
public class XmlItems<T>  {



    private String itemsTag;

    public XmlItems(String itemsTag) {
          this.itemsTag = itemsTag;
    }




    public Element toXML(Document doc, XmlItemI<T> xmlItemI, ImmutableCollection<T> tCollection) {
        Element root = doc.createElement(itemsTag);
        for (T t : tCollection) {
             Element tElem = xmlItemI.toXml(t, doc);
             root.appendChild(tElem);
        }
        return root;
    }

    public ImmutableCollection<T> loadFromXML(Element parent, XmlItemI<T> xmlItemI) {
        Collection<T> tCollection = new ArrayList<T>();
        Element root = XmlUtil.getFirstElementsByTagName(parent, itemsTag);
        NodeList list  = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if ((node.getNodeType() == Node.ELEMENT_NODE) && (node.getNodeName().equals(xmlItemI.getElemName()))) {
                tCollection.add(xmlItemI.fromXml((Element)node));
            }
        }
        return ImmutableList.copyOf(tCollection);
    }


}
