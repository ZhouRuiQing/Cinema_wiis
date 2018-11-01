package com.bwie.cinema_wiis.fragment.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.bwie.cinema_wiis.mvp.view.apdater.viewpager.MyComingAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

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

    private void initView() {

        comingPresent = new ComingPresent(this);
        comingPresent.getComingMovie(1, 10);
    }


    @Override
    public void Sueecss(ComingMovie comingMovie) {

        final List<ComingMovie.ResultBean> list = comingMovie.getResult();

        viewPagerThree.setAdapter(new MyComingAdapter(list, getActivity()));
        viewPagerThree.setPageMargin(20);
        viewPagerThree.setOffscreenPageLimit(list.size());
        //设置画廊模式
        viewPagerThree.setPageTransformer(true,new ViewpageTransformer());

        //左右都有图
        viewPagerThree.setCurrentItem(1);

        //viewpager左右两边滑动无效的处理
        linLayoutThree.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPagerThree.dispatchTouchEvent(event);
            }
        });

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

    @Override
    public void Error(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
