package phase1.my.app.com.myapplication.view.memo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import phase1.my.app.com.myapplication.view.person.PersonItem;
import phase1.my.app.common.list.ItemsCfgListFragment;
import phase1.my.app.common.list.ItemsFileStoreHandler;

/**
 * Created by Owner on 23/10/2014.
 */
public class MemoListFragment extends ItemsCfgListFragment<Memo, MemoItem, MemoItemView> {


    private static final String fileName = "memostfile";

    public static final String EXTRA_DATA_PERSON_ITEM = "person_item";


    public MemoListFragment() {
        super(new ItemsFileStoreHandler<MemoItem>(MemoItem.getXmlBuilder(), "MEMOS"),MemoConfigActivity.class);
    }

    public void onViewCreated(android.view.View  view , android.os.Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                view.setSelected(true);
                MemoItemView item=  (MemoItemView)adapterView.getItemAtPosition(position);
                edit(item.getItem());
            }
        });
    }

    @Override
    protected String getFileName() {
       PersonItem item = getPersonItem();
       return fileName + item.getKey().toString();
    }

    public PersonItem getPersonItem() {
        return (PersonItem)this.getArguments().get(EXTRA_DATA_PERSON_ITEM);
    }

    @Override
    protected MemoItemView createViewItem(MemoItem item, ItemsCfgListFragment list) {
        return new MemoItemView(item);
    }

    @Override
    protected void putExtraDataToNewItemAct(Intent intent) {
        intent.putExtra(EXTRA_DATA_PERSON_ITEM, getPersonItem());
    }

    @Override
    protected void putExtraDataToEditItemAct(Intent intent) {
        intent.putExtra(EXTRA_DATA_PERSON_ITEM, getPersonItem());
    }





}
