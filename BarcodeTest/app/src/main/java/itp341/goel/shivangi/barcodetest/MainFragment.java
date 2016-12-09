package itp341.goel.shivangi.barcodetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.Random;

/**
 * Created by Shivangi on 12/6/2016.
 */
public class MainFragment extends Fragment {
    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        Bundle args = new Bundle();

        MainFragment f = new MainFragment();
        f.setArguments(args);

        return f;
    }

    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView barcodeValue;
    private TextView barcodeFormat;
    private Button buttonReadBarcode;
    private Button buttonFavorites;


    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final int FAVORITES = 1;
    private static final String TAG = "BarcodeMain";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Log.d(TAG, "onCreateView");

        statusMessage = (TextView)v.findViewById(R.id.status_message);
        barcodeValue = (TextView)v.findViewById(R.id.barcode_value);
        barcodeFormat = (TextView)v.findViewById(R.id.barcode_format);

        autoFocus = (CompoundButton) v.findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) v.findViewById(R.id.use_flash);
        buttonReadBarcode = (Button) v.findViewById(R.id.read_barcode);
        buttonFavorites = (Button) v.findViewById(R.id.button_favorites);

        //findViewById(R.id.read_barcode).setOnClickListener(this);
        buttonReadBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BarcodeCaptureActivity.class);
                intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
                intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());

                startActivityForResult(intent, RC_BARCODE_CAPTURE);
            }
        });

        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FavoritesActivity.class);


                startActivity(intent);
            }
        });

        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.read_barcode) {
//            // launch barcode activity.
//            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
//            intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
//            intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());
//
//            startActivityForResult(intent, RC_BARCODE_CAPTURE);
//        }
//
//    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * createPendingResult
     * setResult(int)
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    statusMessage.setText(R.string.barcode_success);
                    barcodeValue.setText(barcode.displayValue);
                    barcodeFormat.setText(Integer.toString(barcode.describeContents()));

                    Random r = new Random();
                    int num = r.nextInt(2);
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    intent.putExtra(ResultActivity.EXTRA_INT, num);
                    intent.putExtra(ResultActivity.EXTRA_BARCODE, barcode.displayValue);

                    startActivity(intent);
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    statusMessage.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
//        else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }

    }
}
