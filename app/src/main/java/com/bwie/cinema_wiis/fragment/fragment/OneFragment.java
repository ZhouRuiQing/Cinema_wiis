package com.bwie.cinema_wiis.fragment.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bwie.cinema_wiis.R;
import com.bwie.cinema_wiis.mvp.model.bean.Movieinfo;
import com.bwie.cinema_wiis.mvp.present.HotMoviePresent;
import com.bwie.cinema_wiis.mvp.view.IView.IHotMvoieView;
import com.bwie.cinema_wiis.mvp.view.ViewpageTransformer;
import com.bwie.cinema_wiis.mvp.view.apdater.MovieApdater;
import com.bwie.cinema_wiis.mvp.view.apdater.viewpager.MyHuaAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements IHotMvoieView {

    Unbinder unbinder;
    @BindView(R.id.simp_viewgs)
    SimpleDraweeView simpViewgs;
    @BindView(R.id.view_pagerone)
    ViewPager viewPagerone;
    @BindView(R.id.linLayout)
    RelativeLayout linLayout;
    @BindView(R.id.one_Rcecyler_View)
    RecyclerView oneRcecylerView;
    @BindView(R.id.simp_View)
    SmartRefreshLayout simpView;
    private HotMoviePresent present;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_one, container, false);
        initData();
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    private void initData() {
        present = new HotMoviePresent(this);
        present.getHoMovie(1, 20);
    }

    @Override
    public void success(Movieinfo movieinfo) {

        Log.i("aaa", movieinfo.getResult().toString() + "");
        final List<Movieinfo.ResultBean> list = movieinfo.getResult();
        oneViewger(list);
        OneMovie(list);

    }

    private void oneViewger(final List<Movieinfo.ResultBean> list) {
        viewPagerone.setAdapter(new MyHuaAdapter(list, getActivity()));
        viewPagerone.setPageMargin(20);
        viewPagerone.setOffscreenPageLimit(list.size());
        //设置画廊模式
        viewPagerone.setPageTransformer(true, new ViewpageTransformer());
        //左右都有图
        viewPagerone.setCurrentItem(1);

        //viewpager左右两边滑动无效的处理
        linLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPagerone.dispatchTouchEvent(event);
            }
        });

        String imageUrl = list.get(0).getImageUrl();
        showUrlBlur(imageUrl);
        viewPagerone.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                String imageUrl = list.get(position).getImageUrl();
                showUrlBlur(imageUrl);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    private void OneMovie(List<Movieinfo.ResultBean> list) {
        MovieApdater apdater = new MovieApdater(getActivity(), list);
        oneRcecylerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        oneRcecylerView.setAdapter(apdater);
    }

    /**
     * @param imageUrl 高斯模糊
     */
    private void showUrlBlur(String imageUrl) {
        try {
            Uri uri = Uri.parse(imageUrl);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(2, 30))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(simpViewgs.getController())
                    .setImageRequest(request)
                    .build();

            simpViewgs.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Error(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    //黏性事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(Integer integer) {

        if (integer % 2 == 0) {
            simpView.setVisibility(View.GONE);
            simpViewgs.setVisibility(View.VISIBLE);
            viewPagerone.setVisibility(View.VISIBLE);
        } else {
            viewPagerone.setVisibility(View.GONE);
            simpView.setVisibility(View.VISIBLE);
            simpViewgs.setVisibility(View.GONE);
        }
    }
}
