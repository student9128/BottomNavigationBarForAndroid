package com.kevin.tech.bottomnavigationbarforandroid.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.tech.bottomnavigationbarforandroid.R;

import java.util.List;

/**
 * Created by Kevin on 2016/11/30.
 * Blog:http://blog.csdn.net/student9128
 * Description: adapter for ViewPager of TabLayoutFragment
 */

public class TabLayoutFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTabList;
    private Context mContext;
    private List<Fragment> mFragments;
    private ImageView mTabIcon;
    private TextView mTabText;
    private int[] mTabImgs;
    private LinearLayout mTabView;

    public TabLayoutFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabLayoutFragmentAdapter(FragmentManager fm, List<String> tabList, Context context, List<Fragment> fragments, int[] tabImgs) {
        super(fm);
        mTabList = tabList;
        mContext = context;
        mFragments = fragments;
        mTabImgs = tabImgs;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }

    /**
     * set the tab view
     *
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_tab_view, null);
        mTabView = (LinearLayout) view.findViewById(R.id.ll_tab_view);
        mTabText = (TextView) view.findViewById(R.id.tv_tab_text);
        mTabIcon = (ImageView) view.findViewById(R.id.iv_tab_icon);
        mTabText.setText(mTabList.get(position));
        mTabIcon.setImageResource(mTabImgs[position]);
        if (0 == position) {//the default color of item home is green
            mTabText.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            mTabIcon.setImageResource(R.drawable.home_fill);
        }
        return view;
    }
}
