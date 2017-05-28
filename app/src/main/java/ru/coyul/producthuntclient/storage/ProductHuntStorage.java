package ru.coyul.producthuntclient.storage;

import java.util.List;

import ru.coyul.producthuntclient.model.Product;

public class ProductHuntStorage {

    private List<Product> mLoadedList;

    public boolean isReady() {
        return mLoadedList != null;
    }

    public List<Product> getLoadedList() {
        return mLoadedList;
    }

    public void setLoadedList(List<Product> loadedList) {
        mLoadedList = loadedList;
    }

}
