package com.project.sinisa.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.sinisa.R;
import com.project.sinisa.config.AuthData;
import com.project.sinisa.penyuluhan.Penyuluhan;
import com.project.sinisa.sewa.Barang;
import com.project.sinisa.sewa.Sewa;

public class Dashboard extends AppCompatActivity {
    TextView nama, nik;
    LinearLayout penyuluhan, sewa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        nama = findViewById(R.id.nama);
        nik = findViewById(R.id.nik);
        nama.setText(AuthData.getInstance(getBaseContext()).getNama_depan());
        nik.setText(AuthData.getInstance(getBaseContext()).getNik());
        penyuluhan = findViewById(R.id.penyuluhan);
        penyuluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Penyuluhan.class));
            }
        });
        sewa = findViewById(R.id.sewa);
        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Barang.class));
            }
        });
    }
}
