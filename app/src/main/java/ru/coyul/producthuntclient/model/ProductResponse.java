package ru.coyul.producthuntclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    @SerializedName("posts")
    private List<Product> mResults;

    public List<Product> getResults() {
        return mResults;
    }

    public void setResults(List<Product> results) {
        this.mResults = results;
    }
}
