package com.project.sinisa.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.project.sinisa.R;

public class Hubungi extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        view = (RelativeLayout) inflater.inflate(R.layout.activity_hubungi, container, false);
        View v = inflater.inflate(R.layout.activity_hubungi, container, false);

//        getActivity().setTitle("Gallery");

        return v;
    }
}