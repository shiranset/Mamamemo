package phase1.my.app.common.view.listadapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import java.util.LinkedHashMap;

/**
 * Created by Owner on 30/10/2014.
 */
public class MapAdapter<K, T extends ViewItemAdapterI & MapAdapter.KeyItem<K>> extends BaseAdapter {

    private final LinkedHashMap<K, T> mData = new LinkedHashMap<K, T>();
    private final Activity activity;

    public MapAdapter(Activity activity) {
        this.activity = activity;
    }

    public void add(T item) {
        mData.put(item.getKey(), item);
    }

    public void remove(T item) {
        mData.remove(item.getKey());
    }

    public ImmutableCollection<T> getItems() {
        return ImmutableList.copyOf(mData.values());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        Object[] mKeys = mData.keySet().toArray(new Object[mData.size()]);
        return mData.get(mKeys[i]);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        return ((ViewItemAdapterI)getItem(i)).getView(activity,convertView,parent);
    }


    public static interface KeyItem<K> {
        public K getKey();
    }
}
