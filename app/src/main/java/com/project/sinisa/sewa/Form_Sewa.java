package com.project.sinisa.sewa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.sinisa.dashboard.Dashboard;
import com.project.sinisa.R;
import com.project.sinisa.config.AppController;
import com.project.sinisa.config.ServerAccess;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Form_Sewa extends AppCompatActivity {
//    deklarasi variabel
    EditText nik, nama, no_telepon, tanggal_sewa, tanggal_kembali, jumlah_hari, harga_sewa, asal, alamat;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sewa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//membuat format date
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        nik = findViewById(R.id.nik);
//        nik.setText(AuthData.getInstance(getBaseContext()).getNik());
        nama = findViewById(R.id.nama);
//        nama.setText(AuthData.getInstance(getBaseContext()).getNama_depan());
        no_telepon= findViewById(R.id.no_telepon);
        tanggal_sewa = findViewById(R.id.tanggal_sewa);
//        menambahkan event klik di tanggal sewa
        tanggal_sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggal_sewa();
            }
        });
        //        menambahkan event fokus di tanggal sewa
        tanggal_sewa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                tanggal_sewa();
            }
        });
        tanggal_kembali = findViewById(R.id.tanggal_kembali);
        tanggal_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggal_kembali();
            }
        });
        tanggal_kembali.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                tanggal_kembali();
            }
        });
        jumlah_hari= findViewById(R.id.jumlah_hari);
        harga_sewa = findViewById(R.id.harga_sewa);
        asal = findViewById(R.id.asal);
        alamat= findViewById(R.id.alamat);
        simpan = findViewById(R.id.simpan);;
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });
    }
    private void simpan(){
        StringRequest senddata = new StringRequest(Request.Method.POST, ServerAccess.SEWA+"?type=add", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("pesan", response);
                    JSONObject res = new JSONObject(response);

                    Toast.makeText(Form_Sewa.this, res.getString("message"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), Dashboard.class);

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("errornyaa ", "" + error);
                Toast.makeText(getBaseContext(), "Gagal Login, "+error, Toast.LENGTH_SHORT).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Intent data = getIntent();
                Map<String, String> params = new HashMap<>();
                params.put("nama", nama.getText().toString());
                params.put("nik", nik.getText().toString());
                params.put("no_telepon", no_telepon.getText().toString());
                params.put("id_barang", data.getStringExtra("kode"));
                params.put("tanggal_sewa", tanggal_sewa.getText().toString());
                params.put("tanggal_kembali", tanggal_kembali.getText().toString());
                params.put("jumlah_hari", jumlah_hari.getText().toString());
                params.put("harga_sewa", harga_sewa.getText().toString());
                params.put("asal", asal.getText().toString());
                params.put("alamat", alamat.getText().toString());
                params.put("nama_barang", data.getStringExtra("nama"));

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(senddata);
    }
    private void tanggal_kembali() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
//        membuat datepicker dialog untuk memilih tanggal
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
//                mengambil data calendar dari smartphone user
                Calendar newDate = Calendar.getInstance();
//                melakukan set data sesua dengan tanggal yag dipilih
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
//                menampilkan data di text tanggal_kembali
                tanggal_kembali.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
//        menampilkan dialog yang sudah di setting sebelumnya
        datePickerDialog.show();
    }
    private void tanggal_sewa() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tanggal_sewa.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
