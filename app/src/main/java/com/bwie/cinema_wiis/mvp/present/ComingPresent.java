package com.bwie.cinema_wiis.mvp.present;

import android.util.Log;

import com.bwie.cinema_wiis.mvp.model.ModelComing;
import com.bwie.cinema_wiis.mvp.model.bean.ComingMovie;
import com.bwie.cinema_wiis.mvp.view.IView.IComingView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ComingPresent extends BasePresent<IComingView> {
    ModelComing modelComing;
    IComingView iComingView;

    public ComingPresent(IComingView iComingView){

        this.iComingView=iComingView;
        modelComing=new ModelComing();
    }

    public void  getComingMovie(int page,int count){

        modelComing.getComingMovie(page,count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ComingMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComingMovie comingMovie) {

                        iComingView.Sueecss(comingMovie);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("aaa",e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
