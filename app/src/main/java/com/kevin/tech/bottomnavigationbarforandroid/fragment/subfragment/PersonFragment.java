package com.kevin.tech.bottomnavigationbarforandroid.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.tech.bottomnavigationbarforandroid.Constants;
import com.kevin.tech.bottomnavigationbarforandroid.R;

/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description:
 */

public class PersonFragment extends Fragment{
    public static PersonFragment newInstance(String s){
        PersonFragment homeFragment = new PersonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_content, container, false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constants.ARGS);
        TextView textView = (TextView) view.findViewById(R.id.fragment_text_view);
        textView.setText(s);
        return view;
    }
}
