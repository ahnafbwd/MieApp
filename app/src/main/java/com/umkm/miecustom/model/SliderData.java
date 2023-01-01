package com.umkm.miecustom.model;

import com.google.gson.annotations.SerializedName;

public class SliderData {
    @SerializedName("image")
    private String imageBanner;

    public SliderData(String imageBanner) {
        this.imageBanner = imageBanner;
    }


    public String getImageBanner() {return imageBanner;}

    public void setImageBanner(String imageBanner) {
        this.imageBanner = imageBanner;
    }

}

