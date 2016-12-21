package com.kevin.tech.bottomnavigationbarforandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.kevin.tech.bottomnavigationbarforandroid.Constants;
import com.kevin.tech.bottomnavigationbarforandroid.R;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment.HomeFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment.LikeFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment.LocationFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment.PersonFragment;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description: Bottom Navigation Bar by BottomNavigationBar
 */

public class NavigationFragment extends Fragment implements BottomNavigationBar.OnTabSelectedListener {


    private BottomNavigationBar mBottomNavigationBar;
    private HomeFragment mHomeFragment;
    private LocationFragment mLocationFragment;
    private LikeFragment mLikeFragment;
    private PersonFragment mPersonFragment;
    private TextView mTextView;

    public static NavigationFragment newInstance(String s) {
        NavigationFragment navigationFragment = new NavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        navigationFragment.setArguments(bundle);
        return navigationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_navigation_bar, container, false);
        mTextView = (TextView) view.findViewById(R.id.activity_text_view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String s = bundle.getString(Constants.ARGS);
            if (!TextUtils.isEmpty(s)) {
                mTextView.setText(s);
            }
        }
        mBottomNavigationBar = (BottomNavigationBar) view.findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home_fill, getString(R.string.item_home)).setInactiveIconResource(R.drawable.home).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.location_fill, getString(R.string.item_location)).setInactiveIconResource(R.drawable.location).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.like_fill, getString(R.string.item_like)).setInactiveIconResource(R.drawable.like).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.person_fill, getString(R.string.item_person)).setInactiveIconResource(R.drawable.person).setActiveColorResource(R.color.colorPrimary).setInActiveColorResource(R.color.black_1))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);

        setDefaultFragment();
        return view;
    }

    /**
     * set the default fagment
     * <p>
     * the content id should not be same with the parent content id
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        HomeFragment homeFragment = mHomeFragment.newInstance(getString(R.string.item_home));
        transaction.replace(R.id.sub_content, homeFragment).commit();

    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance(getString(R.string.item_home));
                }
                beginTransaction.replace(R.id.sub_content, mHomeFragment);
                break;
            case 1:
                if (mLocationFragment == null) {
                    mLocationFragment = LocationFragment.newInstance(getString(R.string.item_location));
                }
                beginTransaction.replace(R.id.sub_content, mLocationFragment);
                break;
            case 2:
                if (mLikeFragment == null) {
                    mLikeFragment = LikeFragment.newInstance(getString(R.string.item_like));
                }
                beginTransaction.replace(R.id.sub_content, mLikeFragment);
                break;
            case 3:
                if (mPersonFragment == null) {
                    mPersonFragment = PersonFragment.newInstance(getString(R.string.item_person));
                }
                beginTransaction.replace(R.id.sub_content, mPersonFragment);
                break;
        }
        beginTransaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
