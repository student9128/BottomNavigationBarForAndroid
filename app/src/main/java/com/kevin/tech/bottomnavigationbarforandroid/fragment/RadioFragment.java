package com.kevin.tech.bottomnavigationbarforandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description: Bottom Navigation Bar by RadioGroup
 */

public class RadioFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private TextView mTextView;
    private RadioButton mRadioHome, mRadioLocation, mRadioLike, mRadioMe;
    private RadioGroup mRadioGroup;

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
        mRadioHome.setChecked(true);
        setHomeState();
        return view;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkId) {
        switch (checkId) {
            case R.id.rb_home:
//                setHomeState();
                break;
            case R.id.rb_location:
//                setLocationState();
                break;
            case R.id.rb_like:
                break;
            case R.id.rb_me:
                break;
        }
        setTabState();
    }

    private void setTabState() {
        setHomeState();
        setLocationState();
        setLikeState();
        setMeState();

    }

    private void setLocationState() {
        if (mRadioLocation.isChecked()) {
            mRadioLocation.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioLocation.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
    }

    private void setHomeState() {
        if (mRadioHome.isChecked()) {
            mRadioHome.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        } else {
            mRadioHome.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
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
