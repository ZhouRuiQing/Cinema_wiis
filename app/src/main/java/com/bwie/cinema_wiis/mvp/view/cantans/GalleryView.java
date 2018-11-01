package com.bwie.cinema_wiis.mvp.view.cantans;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Gallery;

public class GalleryView extends Gallery {

    public GalleryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryView(Context context) {
        this(context, null);
    }

    public GalleryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}