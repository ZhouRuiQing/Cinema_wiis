package com.bwie.cinema_wiis.mvp.view.apdater;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bwie.cinema_wiis.R;
import com.bwie.cinema_wiis.mvp.model.bean.Movieinfo;

import java.util.List;

public class MovieApdater extends RecyclerView.Adapter<MovieHotViewHoder> {
    Context context;
    List<Movieinfo.ResultBean> list;

    public MovieApdater(Context context, List<Movieinfo.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MovieHotViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieHotViewHoder hoder = new MovieHotViewHoder(LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false));
        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHotViewHoder holder, int position) {

        holder.ivMovieImage.setImageURI(Uri.parse(list.get(position).getImageUrl()));
        holder.tvTitle.setText(list.get(position).getSummary());
        holder.tvDis.setText(list.get(position).getRank()+"km");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
