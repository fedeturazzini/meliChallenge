package com.challengemeli.view.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.challengemeli.R;

public class ImageHelper {

    public static void loadImage (ImageView view, String url, CircularProgressDrawable progressDrawable) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.drawable.ic_photo_holder);
        Glide.with(view.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(view);
    }

    public static CircularProgressDrawable getProgressDrawable (Context context) {
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();

        return progressDrawable;
    }
}
