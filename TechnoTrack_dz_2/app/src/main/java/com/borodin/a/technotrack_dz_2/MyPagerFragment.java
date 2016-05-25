package com.borodin.a.technotrack_dz_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class MyPagerFragment extends Fragment {

    private int mStartPosition;
    private ViewPager mPager;
    List<ImageData.Image> _data;

    public static MyPagerFragment newInstance(int startPosition) {
        MyPagerFragment fragment = new MyPagerFragment();
        fragment.mStartPosition = startPosition;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tech_pager, container, false);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        return view;
    }



    private void initDataset() {}

}
