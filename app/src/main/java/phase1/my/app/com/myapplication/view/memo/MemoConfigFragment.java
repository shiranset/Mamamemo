package phase1.my.app.com.myapplication.view.memo;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import phase1.my.app.com.myapplication.R;
import phase1.my.app.com.myapplication.view.person.PersonItem;
import phase1.my.app.common.config.ConfigFragment;

/**
 * Created by Owner on 11/11/2014.
 */

public class MemoConfigFragment extends ConfigFragment<MemoItem> {


    private Calendar cal = Calendar.getInstance();
    public MemoConfigFragment() {
       super(R.layout.memo);

    }


    @Override
    protected void update(View view) {
        PersonItem personItem = this.getPersonItem();
        setDate(view, cal);
        setText(view, null);
        setTitle(view, cal, personItem.getItem().getBirthDate(), personItem.getItem().getName());

    }


    @Override
    protected void update(View view, MemoItem item) {
        PersonItem personItem = this.getPersonItem();
        setDate(view, item.getItem().getCreateDate());
        setText(view, item.getItem().getText());
        setTitle(view, item.getItem().getCreateDate(), personItem.getItem().getBirthDate(), personItem.getItem().getName());
    }

    private static void setDate(View view, Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String date = sdf.format(cal.getTime());
        TextView dateTextView = (TextView)view.findViewById(R.id.date_text_view);
        dateTextView.setText(date);
    }

    private static void setText(View view, String text) {
        TextView editTextView = (TextView)view.findViewById(R.id.edit_text_view);
        editTextView.setHint("Enter Text Here");
        if (text != null) {
            editTextView.setText(text);
        }
    }

    private static void setTitle(View view, Calendar cal, Calendar birthDate, String name) {
        long sub = (cal.getTimeInMillis() - birthDate.getTimeInMillis())/ 86400000;
        //long year = ((((sub/1000)/ 60) /60)/24)/365;
        long year = sub / 365;
        long month = (sub % 365)/30;
        TextView pTextView = (TextView)view.findViewById(R.id.person_text_view);
        pTextView.setText(name + " " + year + "." + month);



    }

    @Override
    public MemoItem createItem(MemoItem oldItem) {
        TextView editTextView = (TextView)this.getView().findViewById(R.id.edit_text_view);
        PersonItem personItem = oldItem != null ? oldItem.getItem().getPerson() : getPersonItem();
        Memo memo = new Memo(personItem,cal,editTextView.getText().toString());
        return oldItem == null ? new MemoItem(memo) : new MemoItem(memo, oldItem.getKey());
    }

    public PersonItem getPersonItem() {
        return (PersonItem)getArguments().get(MemoListFragment.EXTRA_DATA_PERSON_ITEM);
    }
}
