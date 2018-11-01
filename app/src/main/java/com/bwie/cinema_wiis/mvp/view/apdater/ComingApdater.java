package com.bwie.cinema_wiis.mvp.view.apdater;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bwie.cinema_wiis.R;
import com.bwie.cinema_wiis.mvp.model.bean.ComingMovie;

import java.util.List;

public class ComingApdater extends RecyclerView.Adapter<MyComingViewHoder> {
    Context context;
    List<ComingMovie.ResultBean> list;

    public ComingApdater(Context context, List<ComingMovie.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyComingViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyComingViewHoder hoder = new MyComingViewHoder(LayoutInflater.from(context).inflate(R.layout.coming_item,parent,false));

        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyComingViewHoder holder, int position) {

        holder.ivMovieImageThree.setImageURI(Uri.parse(list.get(position).getImageUrl()));
        holder.tvTitleThree.setText(list.get(position).getSummary());
        holder.tvDisThree.setText(list.get(position).getRank()+"km");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
