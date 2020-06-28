package com.project.sinisa.akun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.sinisa.R;
import com.project.sinisa.config.AppController;
import com.project.sinisa.config.AuthData;
import com.project.sinisa.config.ServerAccess;
import com.project.sinisa.dashboard.Dashboard;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sign_In extends AppCompatActivity {
    //deklarasi variabel
    EditText username, password;
    Button login;
    ProgressDialog pd;
    TextView daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //inisialisasi variabel
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        daftar = findViewById(R.id.daftar);
//        membuat event onclick jadi ketika i klik event akan menjalankan fungsi dibawah ini
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fungsi dibawah berfungsi untuk mengarahkan ke halaman lain atau berganti halaman
                startActivity(new Intent(getBaseContext(), Sign_Up.class));
            }
        });
        pd = new ProgressDialog(Sign_In.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
        onLogin();
    }
    private void onLogin(){
//        fungsi ini berfungsi untuk mengecek apakah user sudah pernah login apa belum. jika sudah maka akan diarahkan ke halaman dashboard
        if(AuthData.getInstance(this).isLoggedIn()){
            Sign_In.this.finish();
            startActivity(new Intent(getBaseContext(), Dashboard.class));
        }
    }
    private void doLogin(){
        pd.setMessage("Authenticating...");
        pd.setCancelable(false);
        pd.show();
        //mengecek username apakah kosong apa tidak. jika kosong maka akan menampilkan alert
        if (username.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
//            berfungsi untuk menentukan fokus form
            username.setFocusable(true);
            pd.dismiss();

        }else if (password.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            password.setFocusable(true);
            pd.dismiss();

        }else{
//            fungsi dibawah ini berfungsi untuk melakukan request ke api yang sudah tersedia
            StringRequest senddata = new StringRequest(Request.Method.POST, ServerAccess.LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.cancel();
                    try {
//                        fungsi ini berfungsi untuk mengubah string menjadi jsonObject
                        JSONObject res = new JSONObject(response);
                        Log.d("pesan", res.toString());
//                        mengecek data apakah null atau tidak jika tidak null maka akan di eksekusi di blok if dibawah ini
                        if (res.getString("data") != "null") {
//                            berfungsi untuk mengambil object dengan nama data

                            JSONObject r = res.getJSONObject("data");
//                            menyimpan data login ke class authdata
                            AuthData.getInstance(getBaseContext()).setdatauser(r.getString("id"), r.getString("nama"), r.getString("nik"));
//                            menampilkan pesan jika login berhasil
                            Toast.makeText(Sign_In.this, res.getString("message"), Toast.LENGTH_SHORT).show();
//                            berganti halaman setelah login berhasil
                            Intent intent = new Intent(getBaseContext(), Dashboard.class);

                            startActivity(intent);
                            pd.dismiss();
                        }else{
                            pd.dismiss();
                            Toast.makeText(Sign_In.this, res.getString("message"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pd.cancel();

                    Log.e("errornyaa ", "" + error);
                    Toast.makeText(getBaseContext(), "Gagal Login, "+error, Toast.LENGTH_SHORT).show();


                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
//                    mengirim request username dan password ke api
                    params.put("username", username.getText().toString());
                    params.put("password", password.getText().toString());

                    return params;
                }
            };

            AppController.getInstance().addToRequestQueue(senddata);
        }
    }
    @Override
    public void onBackPressed() {
//        startActivity(new Intent(getBaseContext(), Welcome.class));
    }
}
