package itp341.goel.shivangi.barcodetest.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivangi on 12/6/2016.
 */
public class FavoriteSingleton {

    private List<Favorite> mFavorites;
    //android specific
    private Context mContext; // android-specific so we can access resources
    private static FavoriteSingleton sFavorites; //static means this exists APART from any specific instance


    //TODO private singleton constructor
    private FavoriteSingleton(Context c){
        // assign context
        mContext = c;
//        mFavorites = new ArrayList<>();
//        //add dummy data
//        for (int i =0; i<10; i++){
//            Favorite f = new Favorite();
//            f.setBrandName("Coca-Cola");
//            f.setProductName("Dasani Water Bottle");
//            mFavorites.add(f);
//        }
    }

    //TODO Singleton get method
    //this is our factory method
    public static FavoriteSingleton get(Context c){
        if(sFavorites == null){//sngleton has never been created before or never beek asked for
            sFavorites = new FavoriteSingleton(c.getApplicationContext());

        }
        return sFavorites; //because there already exist sSingleton
    }

//    //TODO getFavorites (all items)
//    public ArrayList<Favorite> getFavorites(){
//        return mFavorites;
//    }
//
//    //TODO getFavorite (single item)
//    public Favorite getFavorite(int index){
//        return mFavorites.get(index);
//
//    }
//
//    //TODO addFavorite
//    public void addFavorite(Favorite f){
//        mFavorites.add(f);
//    }
//
//    //TODO removeFavorite
//    public boolean deleteFavorite(int index){
//
//        if(index >= 0 && index < mFavorites.size()){
//            mFavorites.remove(index);
//            return true;
//        }else {
//            return false;
//        }
//
//
//    }
//
//    //TODO updateFavorite
//
//    public void updateCoffeeShop(int index, Favorite f){
//        if (index >= 0 && index < mFavorites.size()){
//            mFavorites.set(index, f);
//        }
//    }


    //TODO Read all items --change to return List
    public List<Favorite> getFavorites() {
//        return mCoffeeShops;
        mFavorites = Favorite.listAll(Favorite.class);
        return mFavorites;

    }

    //TODO Read
    public Favorite getFavorite(long id) {
//    public CoffeeShop getCoffeeShop(int index) {
//        return mCoffeeShops.get(index);
        return Favorite.findById(Favorite.class, id);
    }

    //TODO Create
    public void addFavorite(Favorite cs) {
//        mCoffeeShops.add(cs);
        cs.save();
    }

    //TODO Delete
    public void removeFavorite(long id) {
//    public void removeCoffeeShop(int index) {
//        if (index >= 0 && index < mCoffeeShops.size())
//            mCoffeeShops.remove(index);
        Favorite cs = Favorite.findById(Favorite.class, id);
        if (cs != null) {
            cs.delete();
        }
    }

    //TODO updateCoffeeShop
    public void updateFavorite(long id, Favorite cs) {
//    public void updateCoffeeShop(int index, CoffeeShop cs) {
//        if (index >= 0 && index < mCoffeeShops.size())
//            mCoffeeShops.set(index, cs);
        cs.setId(id);
        cs.save();
    }

}
