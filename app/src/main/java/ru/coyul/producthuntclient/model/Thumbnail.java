package ru.coyul.producthuntclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Thumbnail implements Serializable {

    @SerializedName("image_url")
    @Expose
    private String mUrl;

    public String getThumbnailUrl() {
        return mUrl;
    }

    public void setThumbnailUrl(String mImageUrl) {
        this.mUrl = mImageUrl;
    }
}
