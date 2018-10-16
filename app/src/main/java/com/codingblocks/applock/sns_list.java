package com.codingblocks.applock;

import android.graphics.drawable.Drawable;

public class sns_list {
    Drawable imagesns;
    String namesns;

    public sns_list(Drawable imagesns, String namesns) {
        this.imagesns = imagesns;
        this.namesns = namesns;
    }

    public Drawable getImagesns() {
        return imagesns;
    }

    public void setImagesns(Drawable imagesns) {
        this.imagesns = imagesns;
    }

    public String getNamesns() {
        return namesns;
    }

    public void setNamesns(String namesns) {
        this.namesns = namesns;
    }
}
