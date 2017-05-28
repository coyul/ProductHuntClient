package ru.coyul.producthuntclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Screenshot implements Serializable {

    @SerializedName("300px")
    @Expose
    private String mScreenshotUrl;

    public String getScreenshotUrl() {
        return mScreenshotUrl;
    }

    public void setScreenshotUrl(String mScreenshotUrl) {
        this.mScreenshotUrl = mScreenshotUrl;
    }
}
