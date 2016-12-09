package itp341.goel.shivangi.barcodetest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shivangi on 12/6/2016.
 */
public class RedFragment extends Fragment {
    private static final String TAG = RedFragment.class.getSimpleName();

    //Bundle key
    public static final String ARGS_BARCODE = "args_barcode";

    TextView textBarcode;
    String mBarcode;
    public RedFragment() {
        // Required empty public constructor
    }

    public static RedFragment newInstance(String bar) {
        Bundle args = new Bundle();
        args.putString(ARGS_BARCODE, bar);


        RedFragment f = new RedFragment();
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
        View v = inflater.inflate(R.layout.fragment_red, container, false);

        textBarcode = (TextView) v.findViewById(R.id.text_barcode_red);
        textBarcode.setText(mBarcode);

        return v;
    }

}
