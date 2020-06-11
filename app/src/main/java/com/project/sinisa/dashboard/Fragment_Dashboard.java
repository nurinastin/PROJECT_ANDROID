package com.project.sinisa.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.sinisa.R;
import com.project.sinisa.config.AuthData;
import com.project.sinisa.penyuluhan.Penyuluhan;
import com.project.sinisa.sewa.Barang;

public class Fragment_Dashboard extends Fragment {

    LinearLayout penyuluhan, sewa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        penyuluhan = v.findViewById(R.id.penyuluhan);
        penyuluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Penyuluhan.class));
            }
        });
        sewa = v.findViewById(R.id.sewa);
        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Barang.class));
            }
        });
        return v;
    }
}