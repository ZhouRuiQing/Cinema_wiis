package com.bwie.cinema_wiis.mvp.view.apdater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.cinema_wiis.R;
import com.facebook.drawee.view.SimpleDraweeView;

class MovieHotViewHoder extends RecyclerView.ViewHolder {

    public SimpleDraweeView ivMovieImage;
    public TextView tvTitle;
    public TextView tvDis;


    public MovieHotViewHoder(View itemView) {
        super(itemView);

        ivMovieImage = itemView.findViewById(R.id.iv_movie_image);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDis = itemView.findViewById(R.id.tv_dis);

    }
}
