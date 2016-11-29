package com.kevin.tech.bottomnavigationbarforandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kevin.tech.bottomnavigationbarforandroid.Constants;
import com.kevin.tech.bottomnavigationbarforandroid.R;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment.HomeFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment.LikeFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment.LocationFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment.PersonFragment;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description: Bottom Navigation Bar by RadioGroup
 */

public class RadioFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private TextView mTextView;
    private RadioButton mRadioHome, mRadioLocation, mRadioLike, mRadioMe;
    private RadioGroup mRadioGroup;
    private HomeFragment mHomeFragment;
    private LocationFragment mLocationFragment;
    private LikeFragment mLikeFragment;
    private PersonFragment mPersonFragment;

    public static RadioFragment newInstance(String s) {
        RadioFragment radioFragment = new RadioFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        radioFragment.setArguments(bundle);
        return radioFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radio_group, container, false);
        mTextView = (TextView) view.findViewById(R.id.activity_text_view);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        mRadioHome = (RadioButton) view.findViewById(R.id.rb_home);
        mRadioLocation = (RadioButton) view.findViewById(R.id.rb_location);
        mRadioLike = (RadioButton) view.findViewById(R.id.rb_like);
        mRadioMe = (RadioButton) view.findViewById(R.id.rb_me);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String s = bundle.getString(Constants.ARGS);
            if (!TextUtils.isEmpty(s)) {
                mTextView.setText(s);
            }
        }
        mRadioGroup.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onStart() {
        setDefaultFragment();//写在onCreateView里面，当页面跑到其他Fragment再回来就不会生效
        super.onStart();
    }

    private void setDefaultFragment() {
        mRadioHome.setChecked(true);
        mRadioLocation.setChecked(false);
        mRadioLike.setChecked(false);
        mRadioMe.setChecked(false);
        if (mRadioHome.isChecked()) {
            setTabState();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            mHomeFragment = mHomeFragment.newInstance(getString(R.string.item_home));
            transaction.replace(R.id.sub_content, mHomeFragment).commit();
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkId) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (checkId) {
            case R.id.rb_home:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance(getString(R.string.item_home));
                }
                transaction.replace(R.id.sub_content, mHomeFragment);
                break;
            case R.id.rb_location:
                if (mLocationFragment == null) {
                    mLocationFragment = LocationFragment.newInstance(getString(R.string.item_location));
                }
                transaction.replace(R.id.sub_content, mLocationFragment);
                break;
            case R.id.rb_like:
                if (mLikeFragment == null) {
                    mLikeFragment = LikeFragment.newInstance(getString(R.string.item_like));
                }
                transaction.replace(R.id.sub_content, mLikeFragment);
                break;
            case R.id.rb_me:
                if (mPersonFragment == null) {
                    mPersonFragment = PersonFragment.newInstance(getString(R.string.item_person));
                }
                transaction.replace(R.id.sub_content, mPersonFragment);
                break;
        }
        setTabState();
        transaction.commit();
    }

    private void setTabState() {
        setHomeState();
        setLocationState();
        setLikeState();
        setMeState();

    }

    /**
     * set tab home state
     */
    private void setHomeState() {
        if (mRadioHome.isChecked()) {
            mRadioHome.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioHome.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setLocationState() {
        if (mRadioLocation.isChecked()) {
            mRadioLocation.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioLocation.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setLikeState() {
        if (mRadioLike.isChecked()) {
            mRadioLike.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioLike.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setMeState() {
        if (mRadioMe.isChecked()) {
            mRadioMe.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioMe.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

}
