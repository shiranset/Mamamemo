package phase1.my.app.com.myapplication.view.memo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.Calendar;

import phase1.my.app.com.myapplication.view.person.Person;
import phase1.my.app.com.myapplication.view.person.PersonItem;
import phase1.my.app.common.xml.XmlItemI;
import phase1.my.app.common.xml.XmlUtil;

/**
 * Created by Owner on 08/12/2014.
 */
public class Memo implements Serializable {

    private final String text;
    private final PersonItem person;
    private final Calendar createDate;

    private final static transient XmlBuilder MEMO_ITEM_XML_BUILDER = new XmlBuilder();

    public Memo(PersonItem person, Calendar createDate, String text) {
        this.text = text;
        this.person = person;
        this.createDate = createDate;

    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public PersonItem getPerson() {
        return person;
    }

    public String getText() {
        return text;
    }

    public static XmlItemI<Memo> getXmlBuilder() {
        return MEMO_ITEM_XML_BUILDER;
    }

    public static class XmlBuilder implements XmlItemI<Memo> {

        private static final String XML_ELEM_MEMO = "MEMO";
        private static final String XML_ATTR_TEXT = "TEXT";

        @Override
        public Element toXml(Memo memo, Document doc) {
            Element root = doc.createElement(XML_ELEM_MEMO);
            root.setAttribute(XML_ATTR_TEXT, memo.getText());
            root.appendChild(PersonItem.getXmlBuilder().toXml(memo.getPerson(), doc));
            Element calElem = XmlUtil.XML_CALENDAR_BUILDER.toXml(memo.getCreateDate(), doc);
            root.appendChild(calElem);
            return root;
        }

        @Override
        public Memo fromXml(Element element) {
            Element personElem = XmlUtil.getFirstElementsByTagName(element, PersonItem.getXmlBuilder().getElemName());
            PersonItem item= PersonItem.getXmlBuilder().fromXml(personElem);
            String text = XmlUtil.getStringAttribute(element,XML_ATTR_TEXT,"");
            Element calElem = XmlUtil.getFirstElementsByTagName(element, XmlUtil.XML_CALENDAR_BUILDER.getElemName());
            Calendar cal = XmlUtil.XML_CALENDAR_BUILDER.fromXml(calElem);
            return new Memo(item, cal, text);
        }

        @Override
        public String getElemName() {
            return XML_ELEM_MEMO;
        }
    }
}
