package ru.coyul.producthuntclient;

import android.app.Application;

import ru.coyul.producthuntclient.storage.ProductHuntStorage;

public class ProductHuntApplication extends Application {

    private ProductHuntStorage mProductHuntStorage = new ProductHuntStorage();

    public ProductHuntStorage getProductHuntStorage() {
        return mProductHuntStorage;
    }

}
