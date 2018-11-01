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
import com.bwie.cinema_wiis.mvp.model.bean.ComingMovie;
import com.bwie.cinema_wiis.mvp.present.ComingPresent;
import com.bwie.cinema_wiis.mvp.view.IView.IComingView;
import com.bwie.cinema_wiis.mvp.view.ViewpageTransformer;
import com.bwie.cinema_wiis.mvp.view.apdater.ComingApdater;
import com.bwie.cinema_wiis.mvp.view.apdater.MovieApdater;
import com.bwie.cinema_wiis.mvp.view.apdater.viewpager.MyComingAdapter;
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
public class ThreFragment extends Fragment implements IComingView {


    @BindView(R.id.simp_viewgs_three)
    SimpleDraweeView simpViewgsThree;
    @BindView(R.id.view_pager_three)
    ViewPager viewPagerThree;
    @BindView(R.id.lin_layout_three)
    RelativeLayout linLayoutThree;
    @BindView(R.id.one_Rcecyler_View_three)
    RecyclerView ThreeRcecylerView;
    @BindView(R.id.simp_View_three)
    SmartRefreshLayout simpViewThree;
    private ComingPresent comingPresent;
    private Unbinder unbinder;

    public ThreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thre, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    private void initView() {

        comingPresent = new ComingPresent(this);
        comingPresent.getComingMovie(1, 10);
    }


    @Override
    public void Sueecss(ComingMovie comingMovie) {

        final List<ComingMovie.ResultBean> list = comingMovie.getResult();
        ComingApdater apdater = new ComingApdater(getActivity(), list);
        ThreeRcecylerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ThreeRcecylerView.setAdapter(apdater);
        threeViewpager(list);
    }

    private void threeViewpager(final List<ComingMovie.ResultBean> list) {
        viewPagerThree.setAdapter(new MyComingAdapter(list, getActivity()));
        viewPagerThree.setPageMargin(20);
        viewPagerThree.setOffscreenPageLimit(list.size());
        //设置画廊模式
        viewPagerThree.setPageTransformer(true, new ViewpageTransformer());

        //左右都有图
        viewPagerThree.setCurrentItem(1);

        //viewpager左右两边滑动无效的处理
        linLayoutThree.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPagerThree.dispatchTouchEvent(event);
            }
        });

        String imageUrl = list.get(0).getImageUrl();
        showUrlBlur(imageUrl);
        viewPagerThree.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    private void showUrlBlur(String imageUrl) {
        try {
            Uri uri = Uri.parse(imageUrl);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(2, 30))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(simpViewgsThree.getController())
                    .setImageRequest(request)
                    .build();

            simpViewgsThree.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //黏性事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(Integer integer) {

        if (integer%2==0) {
            simpViewThree.setVisibility(View.GONE);
            simpViewgsThree.setVisibility(View.VISIBLE);
            viewPagerThree.setVisibility(View.VISIBLE);
        } else {
            viewPagerThree.setVisibility(View.GONE);
            simpViewThree.setVisibility(View.VISIBLE);
            simpViewgsThree.setVisibility(View.GONE);
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

}
