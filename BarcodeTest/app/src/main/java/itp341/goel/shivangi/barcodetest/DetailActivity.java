package itp341.goel.shivangi.barcodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Shivangi on 12/6/2016.
 */
public class DetailActivity extends AppCompatActivity{
    public static final String TAG = DetailActivity.class.getSimpleName();

    //TODO how will we pass data from MainListFragment?
    //public static final String EXTRA_POSITION = "extra_position";
    public static final String EXTRA_ID = "extra_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO get intent data
        Intent i = getIntent();
        //-1 is because it's an array index so it should never be -1
        //int position = i.getIntExtra(EXTRA_POSITION, -1);
        long id = i.getLongExtra(EXTRA_ID, -1);

        //Create fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);

        //TODO modify fragment creation
        if (f == null ) {
            //f = new DetailFragment();
            f = DetailFragment.newInstance(id);
        }
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.commit();
    }
}
