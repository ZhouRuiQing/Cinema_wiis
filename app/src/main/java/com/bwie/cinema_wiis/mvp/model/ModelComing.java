package com.bwie.cinema_wiis.mvp.model;

import com.bwie.cinema_wiis.mvp.model.bean.ComingMovie;
import com.bwie.cinema_wiis.mvp.model.utils.HttpUtils;

import io.reactivex.Observable;

public class ModelComing {

    public   Observable<ComingMovie> getComingMovie(int page,int count){

       return HttpUtils.getInstance().api.getComingMovie(page, count);
    }
}
