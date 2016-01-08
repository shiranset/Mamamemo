package phase1.my.app.com.myapplication;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class MyActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }*/

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_memo_new: {
                newMemo();
                return true;
            }
            default:
                return true;
        }

    }*/

    /*private void newMemo() {
        Intent intent = new Intent(this, MemoConfigActivity.class);
        startActivity(intent);
    }*/
}
