package com.bwie.cinema_wiis.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.cinema_wiis.R;
import com.bwie.cinema_wiis.fragment.fragment.OneFragment;
import com.bwie.cinema_wiis.fragment.fragment.ThreFragment;
import com.bwie.cinema_wiis.fragment.fragment.TwoFragment;
import com.bwie.cinema_wiis.mvp.view.apdater.MovieFragmentApdater;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFregment extends Fragment {


    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_tablayout)
    ViewPager vpTablayout;
    Unbinder unbinder;
    @BindView(R.id.tv_top_movie)
    TextView tvTopMovie;
    @BindView(R.id.iv_cut)
    ImageView ivCut;
    private int page=0;

    public MovieFregment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_movie_fregment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        ivCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                EventBus.getDefault().post(page);
            }
        });
        return inflate;
    }

    private void initView() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new ThreFragment());
        MovieFragmentApdater myFragment = new MovieFragmentApdater(getChildFragmentManager());
        myFragment.MyData(list);
        vpTablayout.setAdapter(myFragment);
        vpTablayout.setOffscreenPageLimit(list.size());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());

        tab.setupWithViewPager(vpTablayout);
        tab.getTabAt(0).setText("热门影片");
        tab.getTabAt(1).setText("正在上映");
        tab.getTabAt(2).setText("即将上映");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
