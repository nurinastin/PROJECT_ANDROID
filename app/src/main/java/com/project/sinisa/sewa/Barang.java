package com.project.sinisa.sewa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.sinisa.R;
import com.project.sinisa.config.AppController;
import com.project.sinisa.config.AuthData;
import com.project.sinisa.config.ServerAccess;
import com.project.sinisa.sewa.adapter.Adapter_Barang;
import com.project.sinisa.sewa.adapter.Adapter_Sewa;
import com.project.sinisa.sewa.model.Barang_Model;
import com.project.sinisa.sewa.model.Sewa_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Barang extends AppCompatActivity {
//    fungsi adapter adalah jembatan antara dan AdapterView (contohnya ListView) dengan data
    private Adapter_Barang adapter;
//    fungsi model adalah untuk menentukan data yang mau diambil dan di setting di model
    private List<Barang_Model> list;
//    recycler view adalah sebuah wadah untuk menampilkna data
    private RecyclerView listdata;
    RecyclerView.LayoutManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);
//        deklarasi toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        menambahkan icon kembali
        toolbar.setNavigationIcon(R.drawable.back);
        //menambahkan event klik di toolbat
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listdata = (RecyclerView) findViewById(R.id.listdata);
        listdata.setHasFixedSize(true);
//        inisialisasi arraylist
        list = new ArrayList<>();
        adapter = new Adapter_Barang(this,(ArrayList<Barang_Model>) list, this);
        mManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        listdata.setLayoutManager(mManager);
        listdata.setAdapter(adapter);
        loadJson();
    }
    private void loadJson()
    {
        Intent data = getIntent();
//        melakukan request dari android ke server untuk mengambil data menggunakan method get
        StringRequest senddata = new StringRequest(Request.Method.GET, ServerAccess.BARANG+"?type=list&nik="+ AuthData.getInstance(getBaseContext()).getNik(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject res = null;
                try {
//                     fungsi ini berfungsi untuk mengubah string menjadi jsonObject
                    res = new JSONObject(response);
//                    mengambil raay dengan nama data dan ditampung di arr
                    JSONArray arr = res.getJSONArray("data");
                    if(arr.length() > 0) {
//                        melakukan looping data di array data sesuai dengan jumlah data
                        for (int i = 0; i < arr.length(); i++) {
                            try {
                                JSONObject data = arr.getJSONObject(i);
//                              membuat object barangmodel untuk set dan get nantinya
                                Barang_Model md = new Barang_Model();
//                                memasukkan data dari api ke model
                                md.setKode(data.getString("id_barang"));
                                md.setNama(data.getString("nama_barang"));
                                md.setHarga(data.getString("harga")+" / hari");
                                md.setFoto(ServerAccess.BASE_URL+"sewaalat/images/"+ data.getString("gambar"));
//                                data dari model nanti akan dimasukkan ke list model
                                list.add(md);
                            } catch (Exception ea) {
                                ea.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(Barang.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
//
                    Toast.makeText(Barang.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    Log.d("pesan", "error "+e.getMessage());
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Barang.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                        Log.d("volley", "errornya : " + error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(senddata);
    }
}
