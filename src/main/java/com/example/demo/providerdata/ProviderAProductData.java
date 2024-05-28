package com.example.demo.providerdata;


public class ProviderAProductData {

    private String productID;
    private String productName;
    private String productDescription;

    public ProviderAProductData(String productID, String productName, String productDescription) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
