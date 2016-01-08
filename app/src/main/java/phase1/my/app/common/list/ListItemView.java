package phase1.my.app.common.list;

import phase1.my.app.com.myapplication.view.person.PersonItem;
import phase1.my.app.common.view.listadapter.MapAdapter;
import phase1.my.app.common.view.listadapter.ViewItemAdapterI;

/**
 * Created by Owner on 08/12/2014.
 */
public abstract class ListItemView<T extends ListItem> implements ViewItemAdapterI, MapAdapter.KeyItem<Integer> {

    protected final T item;

    public ListItemView(T item) {
        this.item = item;
    }

    @Override
    public Integer getKey() {
        return item.getKey();
    }

    public T getItem() {
        return item;
    }
}
