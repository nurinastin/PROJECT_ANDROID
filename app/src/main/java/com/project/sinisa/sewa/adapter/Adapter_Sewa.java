package com.project.sinisa.sewa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.sinisa.R;
import com.project.sinisa.penyuluhan.adapter.Adapter_Penyuluhan;
import com.project.sinisa.penyuluhan.model.Penyuluhan_Model;
import com.project.sinisa.sewa.model.Sewa_Model;

import java.util.ArrayList;

public class Adapter_Sewa extends RecyclerView.Adapter<Adapter_Sewa.ViewHolder> {
    private ArrayList<Sewa_Model> listdata;
    private Activity activity;
    private Context context;

    public Adapter_Sewa(Activity activity, ArrayList<Sewa_Model> listdata, Context context) {
        this.listdata = listdata;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public Adapter_Sewa.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_sewa, parent, false);
        Adapter_Sewa.ViewHolder vh = new Adapter_Sewa.ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(Adapter_Sewa.ViewHolder holder, int position) {
        final Adapter_Sewa.ViewHolder x = holder;
        holder.kode.setText(listdata.get(position).getKode());
        holder.nama.setText(listdata.get(position).getNama());
        holder.nik.setText(listdata.get(position).getNik());
        holder.no_telepon.setText(listdata.get(position).getNo_telepon());
        holder.nama_barang.setText(listdata.get(position).getNama_barang());
        holder.tanggal_sewa.setText(listdata.get(position).getTanggal_sewa());
        holder.tanggal_kembali.setText(listdata.get(position).getTanggal_kembali());
        holder.jumlah_hari.setText(listdata.get(position).getJumlah_hari());
        holder.harga_sewa.setText(listdata.get(position).getHarga_sewa());
        holder.asal.setText(listdata.get(position).getAsal());
        holder.alamat.setText(listdata.get(position).getAlamat());
        holder.mContext = context;
        holder.kode.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView kode, nik, nama, no_telepon, nama_barang, tanggal_sewa, tanggal_kembali, jumlah_hari, harga_sewa, asal, alamat;
        ImageView cover;
        Context mContext;

        public ViewHolder(View v) {
            super(v);
            kode = (TextView) v.findViewById(R.id.kode);
            nama = (TextView) v.findViewById(R.id.nama);
            nik = (TextView) v.findViewById(R.id.nik);
            no_telepon = (TextView) v.findViewById(R.id.no_telepon);
            nama_barang = (TextView) v.findViewById(R.id.nama_barang);
            tanggal_sewa = (TextView) v.findViewById(R.id.tanggal_sewa);
            tanggal_kembali = (TextView) v.findViewById(R.id.tanggal_kembali);
            jumlah_hari = (TextView) v.findViewById(R.id.jumlah_hari);
            harga_sewa = (TextView) v.findViewById(R.id.harga_sewa);
            harga_sewa = (TextView) v.findViewById(R.id.harga_sewa);
            asal = (TextView) v.findViewById(R.id.asal);
            alamat = (TextView) v.findViewById(R.id.alamat);
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

