package com.bwie.cinema_wiis.mvp.model;

import com.bwie.cinema_wiis.mvp.model.bean.Movieinfo;
import com.bwie.cinema_wiis.mvp.model.utils.HttpUtils;

import io.reactivex.Observable;

public class ModelMovie {

    public  Observable<Movieinfo> getHotMovie(int page,int count){

       return HttpUtils.getInstance().api.getHotMovie(page,count);
    }
}
