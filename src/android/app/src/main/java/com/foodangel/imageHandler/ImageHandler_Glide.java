package com.foodangel.imageHandler;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageHandler_Glide implements ImageHandler {

    private Context context;
    private int placeHolderDrawable;
    private int errorDrawable;

    public ImageHandler_Glide(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .placeholder(placeHolderDrawable)
                .dontAnimate()
                .error(errorDrawable)
                .fitCenter()
                .into(view);
    }

    @Override
    public void setPlaceHolderDrawable(int placeHolderDrawable) {
        this.placeHolderDrawable = placeHolderDrawable;
    }

    @Override
    public void setErrorDrawable(int errorDrawable) {
        this.errorDrawable = errorDrawable;
    }
}
