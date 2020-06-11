package com.project.sinisa.penyuluhan;

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

public class Pengajuan_Jadwal extends AppCompatActivity {
    EditText nama, nama_instansi, status, tanggal_input, tanggal_output, materi;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_jadwal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nama = findViewById(R.id.nama);
        nama_instansi = findViewById(R.id.nama_instansi);
        simpan = findViewById(R.id.simpan);
        status = findViewById(R.id.status);
        tanggal_input = findViewById(R.id.tanggal_input);
        tanggal_output = findViewById(R.id.tanggal_output);
        materi = findViewById(R.id.materi);
        tanggal_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggal_input();
            }
        });

        tanggal_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                tanggal_input();
            }
        });
        tanggal_output.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                tanggal_output();
            }
        });
        tanggal_output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggal_output();
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });
    }
    private void simpan(){
            StringRequest senddata = new StringRequest(Request.Method.POST, ServerAccess.PENYULUHAN+"?type=add", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("pesan", response);
                        JSONObject res = new JSONObject(response);

                            Toast.makeText(Pengajuan_Jadwal.this, res.getString("message"), Toast.LENGTH_SHORT).show();
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
                    Map<String, String> params = new HashMap<>();
                    params.put("nama", nama.getText().toString());
                    params.put("nama_instansi", nama_instansi.getText().toString());
                    params.put("status", status.getText().toString());
                    params.put("tanggal_input", tanggal_input.getText().toString());
                    params.put("tanggal_output", tanggal_output.getText().toString());
                    params.put("materi", materi.getText().toString());

                    return params;
                }
            };

            AppController.getInstance().addToRequestQueue(senddata);
    }
    private void tanggal_input() {

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
                tanggal_input.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
    private void tanggal_output() {

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
                tanggal_output.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
