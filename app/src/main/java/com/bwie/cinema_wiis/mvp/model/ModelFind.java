package com.bwie.cinema_wiis.mvp.model;

import com.bwie.cinema_wiis.mvp.model.bean.MovieFindinfo;
import com.bwie.cinema_wiis.mvp.model.utils.HttpUtils;

import io.reactivex.Observable;

public class ModelFind {

    public  Observable<MovieFindinfo>  getFindMovie(int page,int count){

        return HttpUtils.getInstance().api.getFindMovie(page, count);

    }
}
