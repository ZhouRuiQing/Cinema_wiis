package com.bwie.cinema_wiis.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bwie.cinema_wiis.R;
import com.bwie.cinema_wiis.fragment.CinemaFregment;
import com.bwie.cinema_wiis.fragment.MemberFragment;
import com.bwie.cinema_wiis.fragment.MovieFregment;
import com.bwie.cinema_wiis.fragment.SetFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar mBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomBar = findViewById(R.id.bottom_bar);

        mBottomBar.init(getSupportFragmentManager(), 750, 1334)
                .setImgSize(100, 100)
                .setFontSize(14)
//                .setTabPadding(10, 6, 4)
//                .setChangeColor(Color.parseColor("#2784E7"),Color.parseColor("#282828"))
                .addTabItem("影片", R.mipmap.ac1, R.mipmap.ac0,MovieFregment.class)
                .addTabItem("影院", R.mipmap.abx, R.mipmap.abw, CinemaFregment.class)
                .addTabItem("会员",R.mipmap.abv, R.mipmap.abu, MemberFragment.class)
                .addTabItem("设置", R.mipmap.ac3, R.mipmap.ac2, SetFragment.class)
//                .isShowDivider(true)
//                .setDividerColor(Color.parseColor("#373737"))
//                .setTabBarBackgroundColor(Color.parseColor("#FFFFFF"))
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        if (position == 1)
                            mBottomBar.setSpot(1, false);
                    }
                })
                .setSpot(1, false)
                .setSpot(2, false);
    }

    public void setShowTabBar(boolean isShow){
        if (isShow){
            mBottomBar.getTabBar().setVisibility(View.VISIBLE);
        }else {
            mBottomBar.getTabBar().setVisibility(View.GONE);
        }

    }

}
