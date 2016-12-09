package itp341.goel.shivangi.barcodetest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import itp341.goel.shivangi.barcodetest.Model.Favorite;
import itp341.goel.shivangi.barcodetest.Model.FavoriteSingleton;

/**
 * Created by Shivangi on 12/6/2016.
 */
public class GreenFragment extends Fragment {
    private static final String TAG = GreenFragment.class.getSimpleName();

    //Bundle key
    public static final String ARGS_BARCODE = "args_barcode";

    TextView textBarcode;
    Button buttonAdd;
    String mBarcode;
    public GreenFragment() {
        // Required empty public constructor
    }

    public static GreenFragment newInstance(String bar) {
        Bundle args = new Bundle();
        args.putString(ARGS_BARCODE, bar);


        GreenFragment f = new GreenFragment();
        f.setArguments(args);

        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //added these  2 lines
        Bundle args = getArguments();
        //available on fragment
        mBarcode = args.getString(ARGS_BARCODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_green, container, false);

        textBarcode = (TextView) v.findViewById(R.id.text_barcode_green);
        buttonAdd = (Button) v.findViewById(R.id.button_add);
        textBarcode.setText(mBarcode);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndClose();
            }
        });

        return v;
    }

    private void saveAndClose() {
        Log.d(TAG, "saveAndClose");
        Favorite cs = new Favorite();
        cs.setProductName("Dasani");
        cs.setBrandName("Coca-Cola");
        FavoriteSingleton.get(getActivity()).addFavorite(cs);

//            if (mId != -1) {    //this was an existing list we should update
//                CoffeeShopSingleton.get(getActivity()).updateCoffeeShop(mId, cs);
//            } else {  //this is a new list we should add
//                CoffeeShopSingleton.get(getActivity()).addCoffeeShop(cs);
//
//            }

            //getActivity().setResult(Activity.RESULT_OK);
           // getActivity().finish();



    }
}
