package phase1.my.app.com.myapplication.view.memo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Owner on 28/10/2014.
 */
public class MemoListActivity extends FragmentActivity {




    public MemoListActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        Fragment fragment =  new MemoListFragment();
        fragment.setArguments(getIntent().getExtras());
        transaction.add(android.R.id.content, fragment);
        transaction.commit();

    }
}
