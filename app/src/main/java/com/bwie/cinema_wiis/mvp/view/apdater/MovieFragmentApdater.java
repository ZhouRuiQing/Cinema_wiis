package com.bwie.cinema_wiis.mvp.view.apdater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MovieFragmentApdater extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;

    public void MyData(ArrayList<Fragment> list){

        this.list=list;
    }
    public MovieFragmentApdater(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
