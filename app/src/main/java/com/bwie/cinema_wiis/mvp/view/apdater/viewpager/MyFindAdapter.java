package com.bwie.cinema_wiis.mvp.view.apdater.viewpager;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.cinema_wiis.mvp.model.bean.MovieFindinfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyFindAdapter extends PagerAdapter {
    List<MovieFindinfo.ResultBean> list;
    Context context;

    public MyFindAdapter(List<MovieFindinfo.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER);
        simpleDraweeView.setImageURI(Uri.parse(list.get(position).getImageUrl()));
        container.addView(simpleDraweeView);

        return simpleDraweeView;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
