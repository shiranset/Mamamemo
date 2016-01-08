package phase1.my.app.com.myapplication.view.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import phase1.my.app.common.list.ItemsCfgListFragment;

/**
 * Created by Owner on 28/10/2014.
 */
public class PersonConfigActivity extends FragmentActivity {

    public PersonConfigActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        Fragment fragment =  new PersonConfigFragment();
        fragment.setArguments(getIntent().getExtras());
        transaction.add(android.R.id.content, fragment);
        transaction.commit();

    }
}
