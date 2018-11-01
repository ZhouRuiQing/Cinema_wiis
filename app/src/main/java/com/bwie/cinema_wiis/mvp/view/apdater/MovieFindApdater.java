package com.bwie.cinema_wiis.mvp.view.apdater;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bwie.cinema_wiis.R;
import com.bwie.cinema_wiis.mvp.model.bean.MovieFindinfo;

import java.util.List;

public class MovieFindApdater extends RecyclerView.Adapter<MyFindViewHoder> {
    Context context;
    List<MovieFindinfo.ResultBean> list;

    public MovieFindApdater(Context context, List<MovieFindinfo.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyFindViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyFindViewHoder hoder = new MyFindViewHoder(LayoutInflater.from(context).inflate(R.layout.find_item,parent,false));
        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyFindViewHoder holder, int position) {


        holder.ivMovieImageTwo.setImageURI(Uri.parse(list.get(position).getImageUrl()));
        holder.tvTitleTwo.setText(list.get(position).getSummary());
        holder.tvDisTwo.setText(list.get(position).getRank()+"km");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
