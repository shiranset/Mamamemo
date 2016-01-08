package phase1.my.app.com.myapplication.view.person;

import phase1.my.app.common.list.ItemsCfgListFragment;
import phase1.my.app.common.list.ItemsFileStoreHandler;

/**
 * Created by Owner on 23/10/2014.
 */
public class PersonListFragment extends ItemsCfgListFragment<Person, PersonItem, PersonItemView> {


    private static final String fileName = "ayeletfile";


    public PersonListFragment() {
        super(new ItemsFileStoreHandler<PersonItem>(PersonItem.getXmlBuilder(), "PERSONS"),PersonConfigActivity.class);
    }


    @Override
    protected String getFileName() {
        return fileName;
    }

    @Override
    protected PersonItemView createViewItem(PersonItem item, ItemsCfgListFragment list) {
        return new PersonItemView(item, list);
    }




}
