package com.bwie.cinema_wiis.mvp.view.apdater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.cinema_wiis.R;
import com.facebook.drawee.view.SimpleDraweeView;

class MyFindViewHoder extends RecyclerView.ViewHolder {

    public SimpleDraweeView ivMovieImageTwo;
    public TextView tvTitleTwo;
    public TextView tvDisTwo;



    public MyFindViewHoder(View itemView) {
        super(itemView);

        ivMovieImageTwo = itemView. findViewById(R.id.iv_movie_image_two);
        tvTitleTwo = itemView. findViewById(R.id.tv_title_two);
        tvDisTwo = itemView. findViewById(R.id.tv_dis_two);

    }
}
