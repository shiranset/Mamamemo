package phase1.my.app.com.myapplication.view.person;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import phase1.my.app.com.myapplication.R;
import phase1.my.app.com.myapplication.view.memo.MemoListActivity;
import phase1.my.app.com.myapplication.view.memo.MemoListFragment;
import phase1.my.app.common.list.ItemsCfgListFragment;
import phase1.my.app.common.list.ListItemView;

/**
 * Created by Owner on 07/12/2014.
 */
public class PersonItemView extends ListItemView<PersonItem> {


    private final ItemsCfgListFragment lstFragment;

    PersonItemView(PersonItem item, ItemsCfgListFragment lstFragment) {
        super(item);
        this.lstFragment = lstFragment;

    }

    @Override
    public View getView(Context context, View convertView, ViewGroup parent) {
        if( convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.person_list_item, parent, false);
        }
        Button infoButton = (Button) convertView.findViewById(R.id.cfg_button);
        TextView textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(item.getItem().toString());
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lstFragment.edit(item);
            }
        });

        Button memosButton = (Button) convertView.findViewById(R.id.memos_button);
        memosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lstFragment.getActivity(), MemoListActivity.class);
                intent.putExtra(MemoListFragment.EXTRA_DATA_PERSON_ITEM, item);
                lstFragment.startActivity(intent);
            }
        });
        return convertView;
    }



    @Override
    public Integer getKey() {
        return item.getKey();
    }

    public PersonItem getItem() {
        return item;
    }

}
