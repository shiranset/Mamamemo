package phase1.my.app.common.list;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.view.MenuItem;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import phase1.my.app.com.myapplication.R;
import phase1.my.app.common.view.listadapter.MapAdapter;


public abstract class ItemsCfgListFragment<K extends Serializable, E extends ListItem<K> & Serializable, T extends ListItemView<E>> extends ListFragment {

    public static final int REQUEST_CODE_CFG_DONE = 1;
    public static final String EXTRA_DATA_ITEM = "item";
    public static final String EXTRA_ACTION_DONE = "action_done";

    private java.lang.Class cfgClass;
    private final ItemsFileStoreHandler<E> itemsFileStoreHandler;

    protected ItemsCfgListFragment(ItemsFileStoreHandler itemsFileStoreHandler, java.lang.Class cfgClass) {
        this.itemsFileStoreHandler = itemsFileStoreHandler;
        this.cfgClass = cfgClass;
    }

    @Override
    public void onViewCreated(android.view.View  view , android.os.Bundle savedInstanceState) {
        MapAdapter<Integer, T> adapter = new MapAdapter<Integer, T>(this.getActivity());
        Collection<E> items = itemsFileStoreHandler.load(getFileName(), this.getActivity());
        if (items != null) {
            for (E p : items) {
                adapter.add(createViewItem(p, this));
            }
        }
        setListAdapter(adapter);
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);
        this.getListView();


    }

    protected abstract String getFileName();

    protected abstract T createViewItem(E item, ItemsCfgListFragment list);



    @Override
    public void onCreateOptionsMenu(android.view.Menu menu, android.view.MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.items_cfg, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new: {
                newItem();
                return true;
            }
            default:
                return true;
        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CFG_DONE && resultCode == Activity.RESULT_OK) {

            E item = (E) data.getSerializableExtra(EXTRA_DATA_ITEM);
            if (item == null) {
                return;
            }
            MapAdapter<Integer, T> adapter = ((MapAdapter<Integer, T>) this.getListAdapter());
            int actionId = (Integer) data.getSerializableExtra(EXTRA_ACTION_DONE);
            if (actionId == R.id.action_delete) {
                adapter.remove(createViewItem(item, this));
            } else {
                adapter.add(createViewItem(item, this));
            }

            ImmutableCollection<T> collection = adapter.getItems();
            Collection newCollection = new ArrayList<E>();
            for (T view : collection) {
                newCollection.add(view.getItem());
            }
            itemsFileStoreHandler.saveToFile(this.getFileName(), this.getActivity(), ImmutableList.copyOf(newCollection));
            adapter.notifyDataSetChanged();
        }

    }


    protected void putExtraDataToNewItemAct(Intent intent) {

    }

    protected void putExtraDataToEditItemAct(Intent intent) {

    }


    private void newItem() {
        Intent intent = new Intent(this.getActivity(), cfgClass);
        putExtraDataToNewItemAct(intent);
        startActivityForResult(intent, REQUEST_CODE_CFG_DONE);
    }

    public void edit(E item) {
        Intent intent = new Intent(this.getActivity(), cfgClass);
        putExtraDataToEditItemAct(intent);
        intent.putExtra(EXTRA_DATA_ITEM, item);
        startActivityForResult(intent, REQUEST_CODE_CFG_DONE);
    }



    public void finish(E item) {
        Intent returnIntent = new Intent();
        if (item != null) {
            returnIntent.putExtra(EXTRA_DATA_ITEM, item);
        }
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }



}