package phase1.my.app.com.myapplication.view.memo;

import java.io.Serializable;

import phase1.my.app.common.list.ListItem;
import phase1.my.app.common.xml.XmlItemI;

/**
 * Created by Owner on 08/12/2014.
 */
public class MemoItem extends ListItem<Memo> implements Serializable {


    private static final transient MemoItemXmlBuilder MEMO_ITEM_XML_BUILDER = new MemoItemXmlBuilder();

    public MemoItem(Memo item, Integer key) {
        super(item, key);
    }

    public MemoItem(Memo item) {
        super(item, null);
    }




    public static XmlItemI<MemoItem> getXmlBuilder() {
        return MEMO_ITEM_XML_BUILDER;
    }

    private static class MemoItemXmlBuilder extends XmlBuilder<MemoItem, Memo> {

        private static final String XML_ELEM_MEMO_VIEW = "MEMO_VIEW";

        private MemoItemXmlBuilder() {
            super(Memo.getXmlBuilder());
        }




        @Override
        public String getElemName() {
            return XML_ELEM_MEMO_VIEW ;
        }

        @Override
        public MemoItem createListItem(Integer key, Memo item) {
            return new MemoItem(item, key);
        }
    }



}
