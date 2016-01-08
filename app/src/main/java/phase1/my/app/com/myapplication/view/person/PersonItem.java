package phase1.my.app.com.myapplication.view.person;


import phase1.my.app.common.list.ListItem;
import phase1.my.app.common.xml.XmlItemI;

/**
 * Created by Owner on 03/11/2014.
 */
public class PersonItem extends ListItem<Person> {


    private transient static final PersonItemXmlBuilder PERSON_ITEM_XML_BUILDER = new PersonItemXmlBuilder();

    public PersonItem(Person person, Integer key) {
        super(person, key);
    }

    public PersonItem(Person person) {
        super(person, null);
    }



    public static XmlItemI<PersonItem> getXmlBuilder() {
        return PERSON_ITEM_XML_BUILDER;
    }

    private static class PersonItemXmlBuilder extends XmlBuilder<PersonItem, Person> {

        private static final String XML_ELEM_PERSON_VIEW = "PERSON_VIEW";

        private PersonItemXmlBuilder() {
            super(Person.getXmlBuilder());
        }


        @Override
        public PersonItem createListItem(Integer key, Person item) {
            return new PersonItem(item, key);
        }

        @Override
        public String getElemName() {
            return XML_ELEM_PERSON_VIEW;
        }
    }



}
