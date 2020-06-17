package com.foodangel.imageHandler;

import android.widget.ImageView;

public interface ImageHandler {
    public abstract void loadImage(String url, ImageView view);
    public abstract void setPlaceHolderDrawable(int placeHolderDrawable);
    public abstract void setErrorDrawable(int errorDrawable);
}
