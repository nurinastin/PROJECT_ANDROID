package com.project.sinisa.sewa.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.sinisa.R;
import com.project.sinisa.sewa.Detail_Barang;
import com.project.sinisa.sewa.Form_Sewa;
import com.project.sinisa.sewa.model.Barang_Model;
import com.project.sinisa.sewa.model.Sewa_Model;

import java.util.ArrayList;

public class Adapter_Barang extends RecyclerView.Adapter<Adapter_Barang.ViewHolder> {
//    mengambil list dari model barang
    private ArrayList<Barang_Model> listdata;
//    menentukan activity
    private Activity activity;
    private Context context;
//    constructor dari adapterbarang
    public Adapter_Barang(Activity activity, ArrayList<Barang_Model> listdata, Context context) {
        this.listdata = listdata;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public Adapter_Barang.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        menentukan view untuk listdata disini kita menggunakan layout dengan nama template barang
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_barang, parent, false);
        Adapter_Barang.ViewHolder vh = new Adapter_Barang.ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(Adapter_Barang.ViewHolder holder, int position) {
        final Adapter_Barang.ViewHolder x = holder;
//        mengambil data dari model yang sudah di set tadi di activity dengan menentukan posisinya
        holder.kode.setText(listdata.get(position).getKode());
        holder.nama.setText(listdata.get(position).getNama());
        holder.harga.setText(listdata.get(position).getHarga());
//        fungsi glide dibawah adalah memuat gambar yang sudah tersedia menggunakan link
                Glide.with(activity)
                .load(listdata.get(position).getFoto())
                .into(holder.cover);
        holder.mContext = context;
//        fungsi dibawah ini untuk menyembunyikan kode
        holder.kode.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
//        deklarasi text di view
        private TextView kode, harga, nama;
        ImageView cover;
        Context mContext;

        public ViewHolder(View v) {
            super(v);
//            inisialisasi view
            kode = (TextView) v.findViewById(R.id.kode);
            nama = (TextView) v.findViewById(R.id.nama);
            harga = (TextView) v.findViewById(R.id.harga);
            cover = (ImageView) v.findViewById(R.id.cover);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent;
//                      mengarahkan halaman ketika di klik di data barang
                        intent = new Intent(v.getContext(), Form_Sewa.class);
                        intent.putExtra("kode", kode.getText().toString());
                        intent.putExtra("nama", nama.getText().toString());
                        v.getContext().startActivity(intent);
                    } catch (Exception e) {
                        Log.d("pesan", "error");
                    }
                }
            });
        }
    }
}

