package phase1.my.app.com.myapplication.view.person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

import phase1.my.app.com.myapplication.view.person.Person;
import phase1.my.app.com.myapplication.view.person.PersonItem;
import phase1.my.app.com.myapplication.R;
import phase1.my.app.common.config.ConfigFragment;
import phase1.my.app.common.list.ItemsCfgListFragment;

/**
 * Created by Owner on 28/10/2014.
 */
public class PersonConfigFragment extends ConfigFragment<PersonItem> {



    public PersonConfigFragment() {
        super(R.layout.person_cfg);
    }

    @Override
    protected void update(View view) {

    }

    @Override
    protected void update(View view, PersonItem item) {
        Person p = item.getItem();
        setDescription(view, p.getName());
        if (p.getBirthDate() != null) {
            setCalendar(view, p.getBirthDate());
        }
    }



    private Calendar getCalendar() {
        DatePicker datePicker =  (DatePicker)this.getView().findViewById(R.id.person_cfg_date_picker);
        Calendar cal = Calendar.getInstance();
        cal.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
        return cal;
    }



    private static void setDescription(View view, String text) {
        TextView textView = (TextView)view.findViewById(R.id.person_cfg_desc_edit_text);
        textView.setText(text);
    }

    private static void setCalendar(View view, Calendar cal) {
        DatePicker datePicker =  (DatePicker)view.findViewById(R.id.person_cfg_date_picker);
        datePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));

    }

    protected void finish(PersonItem personItem, int actionId) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(ItemsCfgListFragment.EXTRA_ACTION_DONE, actionId);
        returnIntent.putExtra(ItemsCfgListFragment.EXTRA_DATA_ITEM, personItem);
        this.getActivity().setResult(Activity.RESULT_OK, returnIntent);
        this.getActivity().finish();
    }

    @Override
    public PersonItem createItem(PersonItem oldItem) {
        TextView view = (TextView)this.getView().findViewById(R.id.person_cfg_desc_edit_text);
        Person person = (new Person.Builder(view.getText().toString()).setBirthDate(getCalendar())).create();

        return oldItem == null ?
                new PersonItem(person) :
                new PersonItem(person, oldItem.getKey());
    }
}
