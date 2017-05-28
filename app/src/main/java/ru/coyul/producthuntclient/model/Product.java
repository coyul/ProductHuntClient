
package ru.coyul.producthuntclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Product implements Serializable {

    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("tagline")
    @Expose
    private String mDescription;

    @SerializedName("votes_count")
    @Expose
    private int mUpVotes;

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail mThumbnailUrl;

    @SerializedName("screenshot_url")
    @Expose
    private Screenshot mScreenshotUrl;

    @SerializedName("redirect_url")
    @Expose
    private String mSiteUrl;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getUpVotes() {
        return mUpVotes;
    }

    public Thumbnail getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public Screenshot getPictureUrl() {
        return mScreenshotUrl;
    }

    public String getSiteUrl() {
        return mSiteUrl;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setUpVotes(int mUpVotes) {
        this.mUpVotes = mUpVotes;
    }

    public void setThumbnailUrl(Thumbnail mThumbnailUrl) {
        this.mThumbnailUrl = mThumbnailUrl;
    }

    public void setPictureUrl(Screenshot mScreenshotUrl) {
        this.mScreenshotUrl = mScreenshotUrl;
    }

    public void setSiteUrl(String mSiteUrl) {
        this.mSiteUrl = mSiteUrl;
    }
}

