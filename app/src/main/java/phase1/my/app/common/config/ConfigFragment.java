package phase1.my.app.common.config;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import java.io.Serializable;

import phase1.my.app.com.myapplication.R;
import phase1.my.app.com.myapplication.view.person.PersonItem;
import phase1.my.app.common.list.ItemsCfgListFragment;
import phase1.my.app.common.list.ListItem;

/**
 * Created by Owner on 14/12/2014.
 */
public abstract class ConfigFragment<T extends Serializable> extends Fragment {

    private final int layoutId;

    protected ConfigFragment(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        View view = inflater.inflate(layoutId, container, false);
        T item = getItem();
        if (item != null) {
            update(view, item);
        } else {
            update(view);
        }
        return view;
    }

    protected abstract void update(View view);

    protected abstract void update(View view, T item);

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        int menuId = getItem() == null ? R.menu.item_cfg_new : R.menu.item_cfg_edit;
        inflater.inflate(menuId, menu);
    }




    public T getItem() {
        if (getArguments() != null && getArguments().containsKey(ItemsCfgListFragment.EXTRA_DATA_ITEM)) {
            return (T) this.getArguments().get(ItemsCfgListFragment.EXTRA_DATA_ITEM);
        }
        return null;
    }


    protected void finish(T item, int actionId) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(ItemsCfgListFragment.EXTRA_ACTION_DONE, actionId);
        returnIntent.putExtra(ItemsCfgListFragment.EXTRA_DATA_ITEM, item);
        this.getActivity().setResult(Activity.RESULT_OK, returnIntent);
        this.getActivity().finish();
    }

    public abstract T createItem(T oldItem);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done: {
                T oldItem = getItem();
                finish(createItem(oldItem),item.getItemId());
                break;
            }
            case R.id.action_delete: {
                T itemT = getItem();
                finish(itemT,item.getItemId());
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
