package com.bwie.cinema_wiis.fragment.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bwie.cinema_wiis.R;
import com.bwie.cinema_wiis.mvp.model.bean.MovieFindinfo;
import com.bwie.cinema_wiis.mvp.model.bean.Movieinfo;
import com.bwie.cinema_wiis.mvp.present.FindPresent;
import com.bwie.cinema_wiis.mvp.view.IView.IFindView;
import com.bwie.cinema_wiis.mvp.view.ViewpageTransformer;
import com.bwie.cinema_wiis.mvp.view.apdater.MovieApdater;
import com.bwie.cinema_wiis.mvp.view.apdater.MovieFindApdater;
import com.bwie.cinema_wiis.mvp.view.apdater.viewpager.MyFindAdapter;
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
public class TwoFragment extends Fragment implements IFindView {

    @BindView(R.id.simp_viewgs_two)
    SimpleDraweeView simpViewgsTwo;
    @BindView(R.id.view_pager_two)
    ViewPager viewPagerTwo;
    @BindView(R.id.li_layout_two)
    RelativeLayout liLayoutTwo;
    Unbinder unbinder;
    @BindView(R.id.two_Rcecyler_View)
    RecyclerView twoRcecylerView;
    @BindView(R.id.simp_View_two)
    SmartRefreshLayout simpViewTwo;
    private FindPresent findPresent;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_two, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    private void initView() {
        findPresent = new FindPresent(this);
        findPresent.getFindMovie(1, 10);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void success(MovieFindinfo movieFindinfo) {

        final List<MovieFindinfo.ResultBean> list = movieFindinfo.getResult();
        initFind(list);
        teoViewPager(list);


    }

    private void teoViewPager(final List<MovieFindinfo.ResultBean> list) {
        viewPagerTwo.setAdapter(new MyFindAdapter(list, getActivity()));
        viewPagerTwo.setPageMargin(20);
        viewPagerTwo.setOffscreenPageLimit(list.size());
        //设置画廊模式
        viewPagerTwo.setPageTransformer(true, new ViewpageTransformer());

        //左右都有图
        viewPagerTwo.setCurrentItem(1);

        //viewpager左右两边滑动无效的处理
        liLayoutTwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPagerTwo.dispatchTouchEvent(event);
            }
        });

        String imageUrl = list.get(0).getImageUrl();
        showUrlBlur(imageUrl);
        viewPagerTwo.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    private void initFind(List<MovieFindinfo.ResultBean> list) {
        MovieFindApdater apdater = new MovieFindApdater(getActivity(),list);
        twoRcecylerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        twoRcecylerView.setAdapter(apdater);
    }

    //高斯模糊
    private void showUrlBlur(String imageUrl) {
        try {
            Uri uri = Uri.parse(imageUrl);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(2, 30))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(simpViewgsTwo.getController())
                    .setImageRequest(request)
                    .build();

            simpViewgsTwo.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //黏性事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(Integer integer) {

        if (integer % 2 == 0) {
            simpViewTwo.setVisibility(View.GONE);
            simpViewgsTwo.setVisibility(View.VISIBLE);
            viewPagerTwo.setVisibility(View.VISIBLE);
        } else {
            viewPagerTwo.setVisibility(View.GONE);
            simpViewTwo.setVisibility(View.VISIBLE);
            simpViewgsTwo.setVisibility(View.GONE);
        }
    }

    @Override
    public void Error(String msg) {

    }
}
