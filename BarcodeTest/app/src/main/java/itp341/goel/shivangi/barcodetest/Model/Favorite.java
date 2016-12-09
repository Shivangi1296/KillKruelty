package itp341.goel.shivangi.barcodetest.Model;

import com.orm.SugarRecord;

/**
 * Created by Shivangi on 12/6/2016.
 */
public class Favorite extends SugarRecord {
    private String productName;
    private String brandName;


    public Favorite(){
        super();
    }

    public Favorite(String product, String brand){
        super();
        this.productName = product;
        this.brandName = brand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
