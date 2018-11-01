package com.bwie.cinema_wiis.mvp.model.utils;


import com.bwie.cinema_wiis.mvp.model.bean.ComingMovie;
import com.bwie.cinema_wiis.mvp.model.bean.MovieFindinfo;
import com.bwie.cinema_wiis.mvp.model.bean.Movieinfo;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    //热门影院
    @GET("movieApi/movie/v1/findHotMovieList")
    Observable<Movieinfo> getHotMovie(@Query("page") int page,@Query("count") int count);
    //正在上映
    @GET("movieApi/movie/v1/findReleaseMovieList")
    Observable<MovieFindinfo> getFindMovie(@Query("page") int page,@Query("count") int count);
    //即将上映
    @GET("movieApi/movie/v1/findComingSoonMovieList")
    Observable<ComingMovie> getComingMovie(@Query("page") int page,@Query("count") int count);
}
