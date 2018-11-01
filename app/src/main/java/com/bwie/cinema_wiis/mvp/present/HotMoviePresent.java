package com.bwie.cinema_wiis.mvp.present;

import android.util.Log;

import com.bwie.cinema_wiis.mvp.view.IView.IHotMvoieView;
import com.bwie.cinema_wiis.mvp.model.bean.Movieinfo;
import com.bwie.cinema_wiis.mvp.model.ModelMovie;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HotMoviePresent extends BasePresent<IHotMvoieView> {

    ModelMovie modelMovie;
    IHotMvoieView iHotMvoieView;

    public HotMoviePresent(IHotMvoieView iHotMvoieView){

        this.iHotMvoieView=iHotMvoieView;
        modelMovie=new ModelMovie();
    }

    public void  getHoMovie(int page,int count){

        modelMovie.getHotMovie(page,count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Movieinfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movieinfo movieinfo) {

                        iHotMvoieView.success(movieinfo);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("xxx",e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
