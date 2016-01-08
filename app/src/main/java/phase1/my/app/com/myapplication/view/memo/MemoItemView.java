package phase1.my.app.com.myapplication.view.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phase1.my.app.com.myapplication.R;
import phase1.my.app.com.myapplication.view.memo.MemoItem;
import phase1.my.app.common.list.ListItemView;

/**
 * Created by Owner on 08/12/2014.
 */
public class MemoItemView extends ListItemView<MemoItem> {


    MemoItemView(MemoItem memoItem) {
        super(memoItem);
    }


    @Override
    public View getView(Context context, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.memos_list_item, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(item.getItem().getText());
        return convertView;
    }


}
