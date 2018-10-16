package com.codingblocks.applock;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class chamber_value {
    private Drawable imageView;
    private String heading, subheading;

    public chamber_value(Drawable imageView, String heading, String subheading) {
        this.imageView = imageView;
        this.heading = heading;
        this.subheading = subheading;
    }

    public Drawable getImageView() {
        return imageView;
    }

    public void setImageView(Drawable imageView) {
        this.imageView = imageView;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }
}