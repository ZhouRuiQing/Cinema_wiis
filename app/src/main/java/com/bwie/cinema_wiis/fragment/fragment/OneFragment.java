package com.bwie.cinema_wiis.fragment.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bwie.cinema_wiis.mvp.view.IView.IHotMvoieView;
import com.bwie.cinema_wiis.R;
import com.bwie.cinema_wiis.mvp.model.bean.Movieinfo;
import com.bwie.cinema_wiis.mvp.present.HotMoviePresent;
import com.bwie.cinema_wiis.mvp.view.ViewpageTransformer;
import com.bwie.cinema_wiis.mvp.view.apdater.viewpager.MyHuaAdapter;
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
public class OneFragment extends Fragment implements IHotMvoieView {

    Unbinder unbinder;
    @BindView(R.id.simp_viewgs)
    SimpleDraweeView simpViewgs;
    @BindView(R.id.view_pagerone)
    ViewPager viewPagerone;
    @BindView(R.id.linLayout)
    RelativeLayout linLayout;
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
      /*  MovieApdater apdater = new MovieApdater(getActivity(),list);
        OneRecylerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        OneRecylerView.setAdapter(apdater);*/
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

        viewPagerone.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                String imageUrl =list.get(position).getImageUrl();
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
    }
}
