package com.bwie.cinema_wiis.mvp.view.IView;

import com.bwie.cinema_wiis.mvp.model.bean.Movieinfo;

public interface IHotMvoieView extends IBaseView {

    void success(Movieinfo movieinfo);
    void Error(String msg);
}
