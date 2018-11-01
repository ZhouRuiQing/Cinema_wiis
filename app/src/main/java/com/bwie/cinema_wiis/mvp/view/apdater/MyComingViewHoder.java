package com.bwie.cinema_wiis.mvp.view.apdater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.cinema_wiis.R;
import com.facebook.drawee.view.SimpleDraweeView;

class MyComingViewHoder extends RecyclerView.ViewHolder {

    public SimpleDraweeView ivMovieImageThree;
    public TextView tvTitleThree;
    public TextView tvDisThree;


    public MyComingViewHoder(View itemView) {
        super(itemView);


        ivMovieImageThree = itemView. findViewById(R.id.iv_movie_image_three);
        tvTitleThree = itemView. findViewById(R.id.tv_title_three);
        tvDisThree = itemView. findViewById(R.id.tv_dis_three);
    }
}
