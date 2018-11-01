package com.bwie.cinema_wiis.mvp.view.IView;

import com.bwie.cinema_wiis.mvp.model.bean.ComingMovie;

public interface IComingView extends IBaseView {

    void Sueecss(ComingMovie comingMovie);
    void Error(String msg);
}
