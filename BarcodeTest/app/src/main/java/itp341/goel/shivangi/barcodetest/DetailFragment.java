package itp341.goel.shivangi.barcodetest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import itp341.goel.shivangi.barcodetest.Model.Favorite;
import itp341.goel.shivangi.barcodetest.Model.FavoriteSingleton;

/**
 * Created by Shivangi on 12/6/2016.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = DetailFragment.class.getSimpleName();

    //Bundle key
    public static final String ARGS_POSITION = "args_position";
    public static final String ARGS_ID = "args_id";

    TextView mProductName;
    TextView mBrandName;
    Button mButtonDeleteListing;

    int mPosition;
    long mID;


    ArrayAdapter<CharSequence> mSpinnerAdapter;

    public DetailFragment() {
        // Required empty public constructor
    }

    //TODO store newInstance input into fragment argument
    //standard mechanism to get data from activity
    public static DetailFragment newInstance(long id) {
        Bundle args = new Bundle();
        //args.putInt(ARGS_POSITION, pos);
        args.putLong(ARGS_ID, id);


        DetailFragment f = new DetailFragment();
        f.setArguments(args);

        return f;
    }

    //TODO read bundle argument
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //added these  2 lines
        Bundle args = getArguments();
        //available on fragment
        //mPosition = args.getInt(ARGS_POSITION);

        mID = args.getLong(ARGS_ID,-1);
        Log.d(TAG, "onCreate: id = " + mID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        //findViews
        mProductName = (TextView) v.findViewById(R.id.text_product);
        mBrandName = (TextView) v.findViewById(R.id.text_brand);
        mButtonDeleteListing = (Button) v.findViewById(R.id.button_delete);


        //button listeners
        mButtonDeleteListing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteAndClose();

            }
        });


        //check if existing(mposition != -1)


//        Favorite f = FavoriteSingleton.get(getActivity()).getFavorite(mPosition);
//        loadData(f);

        if (mID != -1) { //this means we are editing an existing listing
            Favorite cs = FavoriteSingleton.get(getActivity()).getFavorite(mID);
            if (cs != null) { // this means we are editing old record
                loadData(cs);

            }
        }




        return v;
    }


    //TODO load data from existing coffee shop object
    private void loadData(Favorite f) {
        //added in the class
        mProductName.setText(f.getProductName());
        mBrandName.setText(f.getBrandName());


    }



    //TODO Listing should be deleted (only it was an existing entry, not if it was new))
    private void deleteAndClose() {
        Log.d(TAG, "deleteAndClose");
//        if(mPosition !=-1) {
//            FavoriteSingleton.get(getActivity()).deleteFavorite(mPosition);
//        }
//        getActivity().setResult(Activity.RESULT_OK);
//        getActivity().finish();
        if (mID != -1) {    //this was an existing list we should update
            FavoriteSingleton.get(getActivity()).removeFavorite(mID);
        }

        getActivity().setResult(Activity.RESULT_OK);//our notification that the user hit save or delete
        getActivity().finish();


    }
}
