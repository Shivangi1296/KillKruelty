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
public class ResultActivity extends AppCompatActivity {
    public static final String EXTRA_INT = "result.int";
    public static final String EXTRA_BARCODE = "result.barcode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO get intent data
        Intent i = getIntent();
        //-1 is because it's an array index so it should never be -1
        int num = i.getIntExtra(EXTRA_INT, -1);
        String barcode = i.getStringExtra(EXTRA_BARCODE);
        //Create fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);

        //TODO modify fragment creation
        if (f == null ) {
            //f = new DetailFragment();
            if(num==0){
                f = GreenFragment.newInstance(barcode);
            }
            else if(num==1){
                f = RedFragment.newInstance(barcode);
            }
            else if(num==2){
                f = YellowFragment.newInstance(barcode);
            }
            else {
            }

        }
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.commit();
    }
}

