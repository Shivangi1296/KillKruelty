package itp341.goel.shivangi.barcodetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itp341.goel.shivangi.barcodetest.Model.Favorite;
import itp341.goel.shivangi.barcodetest.Model.FavoriteSingleton;

/**
 * Created by Shivangi on 12/6/2016.
 */
public class FavoritesFragment extends Fragment {

    private static final String TAG = FavoritesFragment.class.getSimpleName();
    ListView mListView;

    //TODO create array and adapter
    private List<Favorite> mFavorites;
    private  FavoriteAdapter mAdapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        Bundle args = new Bundle();

        FavoritesFragment f = new FavoritesFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        Log.d(TAG, "onCreateView");

        //find views
        //mButtonAdd = (Button) v.findViewById(R.id.button_add);
        mListView = (ListView) v.findViewById(R.id.listView);

        mFavorites = FavoriteSingleton.get(getActivity()).getFavorites();
        mAdapter = new FavoriteAdapter(getActivity(),
                android.R.layout.simple_list_item_1,
                mFavorites);

        mListView.setAdapter(mAdapter);



        //TODO What happens when user clicks add?


        //TODO create listview item click listener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "mListView: onListItemClick");

                //create the intent that will launch the detail view
                //need to pass in the position (index) that was clicked
                Intent i = new Intent(getActivity(), DetailActivity.class);
                //i.putExtra(DetailActivity.EXTRA_POSITION, position);
                i.putExtra(DetailActivity.EXTRA_ID, id);
                startActivityForResult(i, 0);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);

        if (resultCode == Activity.RESULT_OK) { // this means user saved
            Log.d(TAG, "onActivityResult: requestCode: " + requestCode);


            Log.d(TAG, "favorites: " + mFavorites);

            mFavorites = FavoriteSingleton.get(getActivity()).getFavorites();
            mAdapter.clear();
            mAdapter.addAll(mFavorites);
            mAdapter.notifyDataSetChanged();

        }
        //else means they pressed back and didn't save

    }

    public class FavoriteAdapter extends ArrayAdapter<Favorite> {
        //arrayadapters are built on arraylist for data
       List<Favorite> mFavoritesInAdapter;


        //overloaded constructor
        //constructor for custom adapter are usually similar to this
        public FavoriteAdapter(Context context, int resource, List<Favorite> objects){
            super(context, resource, objects);
            this.mFavoritesInAdapter = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //check if row has been created ever
            //convertviw represrents a single row layout
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.list_item_row, null);
            }

//            if(convertView == null){
//                convertView = getActivity().getLayoutInflater()
//                        .inflate(R.layout.list_item_row, null);
//            }
            //load data from the model (AKA the Arraylist) into a single row
            //access current row of model data

            Favorite cs = mFavoritesInAdapter.get(position);


            TextView textProductName  = (TextView) convertView.findViewById(R.id.list_item_product_name);
            //TextView textBrandName  = (TextView) convertView.findViewById(R.id.list_item_city);

            textProductName.setText(cs.getProductName());
            //textBrandName.setText(cs.getBrandName());

            return convertView;
        }

        @Override
        public long getItemId(int position) {
            Favorite cs  = mFavoritesInAdapter.get(position);
            long id = cs.getId();
            Log.d(TAG, "FavoritesAdapter getItemId = " + id);
            return id;

        }


    }
}
