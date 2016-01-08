package phase1.my.app.com.myapplication.view.person;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.Calendar;

import phase1.my.app.com.myapplication.R;
import phase1.my.app.common.xml.XmlItemI;
import phase1.my.app.common.xml.XmlUtil;

/**
 * Created by Owner on 06/10/2014.
 */
public class Person implements Serializable {





    private final static transient XmlBuilder xmlBuilder = new XmlBuilder();


    private final String name;



    private final Calendar birthDate;

    private Person(String name, Calendar birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }


    public Calendar getBirthDate() {
        return birthDate;
    }

    public String toString() {
        return name;
    }

    public static XmlItemI<Person> getXmlBuilder() {
        return xmlBuilder;
    }


    public static class Builder {

        private final String desc;
        private Calendar birthDate;

        public Builder(String desc)  {
            this.desc = desc;
        }

        public Builder setBirthDate(Calendar birthDate) {
            this.birthDate = birthDate;
            return this;
        }


        public Person create() {
            return new Person( desc, birthDate);
        }

    }

    public static class XmlBuilder implements XmlItemI<Person> {


        private static final String XML_ELEM_PERSON = "PERSON";
        private static final String XML_ATTR_DESC = "DESC";

        @Override
        public Element toXml(Person p, Document doc) {
            Element root = doc.createElement(XML_ELEM_PERSON);
            if (p.getBirthDate() != null) {
                Element calElem = XmlUtil.XML_CALENDAR_BUILDER.toXml(p.getBirthDate(), doc);
                root.appendChild(calElem);
            }
            root.setAttribute(XML_ATTR_DESC,p.getName());
            return root;
        }

        @Override
        public Person fromXml(Element element) {
           String desc = XmlUtil.getStringAttribute(element,XML_ATTR_DESC,"");
           Element calElem = XmlUtil.getFirstElementsByTagName(element, XmlUtil.XML_CALENDAR_BUILDER.getElemName());
           Person.Builder builder = new Person.Builder(desc);
           if (calElem != null) {
               Calendar cal = XmlUtil.XML_CALENDAR_BUILDER.fromXml(calElem);
               builder.setBirthDate(cal);
           }
           return builder.create();
        }

        @Override
        public String getElemName() {
            return XML_ELEM_PERSON;
        }
    }

}
