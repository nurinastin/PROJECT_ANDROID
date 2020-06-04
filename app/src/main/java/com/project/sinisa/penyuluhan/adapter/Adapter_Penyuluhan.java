package com.project.sinisa.penyuluhan.adapter;

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

import com.project.sinisa.R;
import com.project.sinisa.penyuluhan.model.Penyuluhan_Model;

import java.util.ArrayList;

public class Adapter_Penyuluhan extends RecyclerView.Adapter<Adapter_Penyuluhan.ViewHolder> {
    private ArrayList<Penyuluhan_Model> listdata;
    private Activity activity;
    private Context context;

    public Adapter_Penyuluhan(Activity activity, ArrayList<Penyuluhan_Model> listdata, Context context) {
        this.listdata = listdata;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public Adapter_Penyuluhan.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_penyuluhan, parent, false);
        Adapter_Penyuluhan.ViewHolder vh = new Adapter_Penyuluhan.ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(Adapter_Penyuluhan.ViewHolder holder, int position) {
        final Adapter_Penyuluhan.ViewHolder x = holder;
        holder.kode.setText(listdata.get(position).getKode());
        holder.nama.setText(listdata.get(position).getNama());
        holder.nama_instansi.setText(listdata.get(position).getNama_instansi());
        holder.tanggal_pengajuan.setText(listdata.get(position).getTanggal_input());
        holder.tanggal_pelaksanaan.setText(listdata.get(position).getTanggal_output());
        holder.materi.setText(listdata.get(position).getMateri());
//        Glide.with(activity)
//                .load(listdata.get(position).getFoto())
//                .into(holder.cover);
        holder.mContext = context;
        holder.kode.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView kode, nama, nama_instansi, tanggal_pengajuan, tanggal_pelaksanaan, materi;
        ImageView cover;
        Context mContext;

        public ViewHolder(View v) {
            super(v);
            kode = (TextView) v.findViewById(R.id.kode);
            nama = (TextView) v.findViewById(R.id.nama);
            nama_instansi = (TextView) v.findViewById(R.id.nama_instansi);
            tanggal_pengajuan = (TextView) v.findViewById(R.id.tanggal_pengajuan);
            tanggal_pelaksanaan = (TextView) v.findViewById(R.id.tanggal_pelaksanaan);
            materi = (TextView) v.findViewById(R.id.materi);
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Intent intent;
//                        intent = new Intent(v.getContext(), Detail_Hewan.class);
//                        intent.putExtra("kode", kode.getText().toString());
//                        v.getContext().startActivity(intent);
//                    } catch (Exception e) {
//                        Log.d("pesan", "error");
//                    }
//                }
//            });
        }
    }
}

