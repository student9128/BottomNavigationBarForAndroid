package com.kevin.tech.bottomnavigationbarforandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.tech.bottomnavigationbarforandroid.Constants;
import com.kevin.tech.bottomnavigationbarforandroid.R;

/**
 * Created by Kevin on 2016/11/29.
 * Blog:http://blog.csdn.net/student9128
 * Description: Bottom Navigation Bar by ViewPager
 */

public class ViewPagerFragment extends Fragment {
    private ViewPager mViewPager;
    private TextView mTHome, mTLocation, mTLike, mTMe, mTextView;

    public static ViewPagerFragment newInstance(String s) {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        viewPagerFragment.setArguments(bundle);
        return viewPagerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        mTextView = (TextView) view.findViewById(R.id.activity_text_view);
        mTHome = (TextView) view.findViewById(R.id.tv_home);
        mTLocation = (TextView) view.findViewById(R.id.tv_location);
        mTLike = (TextView) view.findViewById(R.id.tv_like);
        mTMe = (TextView) view.findViewById(R.id.tv_person);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String s = bundle.getString(Constants.ARGS);
            if (!TextUtils.isEmpty(s)) {
                mTextView.setText(s);
            }
        }
        return view;
    }
}
