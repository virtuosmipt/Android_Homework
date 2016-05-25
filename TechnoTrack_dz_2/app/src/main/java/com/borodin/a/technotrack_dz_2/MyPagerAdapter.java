package com.borodin.a.technotrack_dz_2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by a.borodin on 25.05.2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    List<ImageData.Image> techItemList;
    public MyPagerAdapter(Context context, FragmentManager fm, List<ImageData.Image> techItemList) {
        super(fm);
        this.techItemList = techItemList;
    }

    @Override
    public Fragment getItem(int position) {
        return MyPagerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return techItemList.size();
    }
}
