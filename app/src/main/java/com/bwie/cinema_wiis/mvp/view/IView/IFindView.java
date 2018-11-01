package com.bwie.cinema_wiis.mvp.view.IView;

import com.bwie.cinema_wiis.mvp.model.bean.MovieFindinfo;

public interface IFindView extends IBaseView {

    void success(MovieFindinfo movieFindinfo);
    void Error(String msg);
}
