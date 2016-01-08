package phase1.my.app.com.myapplication.view.memo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by Owner on 06/11/2014.
 */
public class MemoConfigActivity extends FragmentActivity {

    public MemoConfigActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        Fragment fragment =  new MemoConfigFragment();
        fragment.setArguments(getIntent().getExtras());
        transaction.add(android.R.id.content, fragment);
        transaction.commit();

    }






}
