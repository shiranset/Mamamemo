package phase1.my.app.common.view.listadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Owner on 06/11/2014.
 */
public class ArrayAdapter<E extends ViewItemAdapterI> extends BaseAdapter {

    private final ArrayList<E> collection = new ArrayList<E>();
    private final Context context;

    public ArrayAdapter(Context context) {
        this.context = context;
    }

    public void add(E item) {
        collection.add(item);
    }

    public void add(int index, E item) {
        collection.add(index, item);
    }

    public void remove(int i) {
        collection.remove(i);
    }

    @Override
    public int getCount() {
        return collection.size();
    }

    @Override
    public Object getItem(int i) {
        return collection.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        return ((ViewItemAdapterI)getItem(i)).getView(context,convertView,parent);
    }
}
