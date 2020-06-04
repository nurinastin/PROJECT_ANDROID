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
import com.project.sinisa.config.ServerAccess;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sign_Up extends AppCompatActivity {
    EditText nik, nama, username, password, alamat;
    Button register;
    ProgressDialog pd;
    TextView masuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nik = findViewById(R.id.nik);
        nama = findViewById(R.id.nama);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        masuk = findViewById(R.id.masuk);
        alamat = findViewById(R.id.alamat);
        register = findViewById(R.id.register);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Sign_In.class));
            }
        });
        pd = new ProgressDialog(Sign_Up.this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
    }
    private void doRegister(){
        pd.setMessage("Authenticating...");
        pd.setCancelable(false);
        pd.show();
        if (nik.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Nik Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            nik.setFocusable(true);
            pd.dismiss();

        }else if (nama.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            nama.setFocusable(true);
            pd.dismiss();

        }else if (username.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            username.setFocusable(true);
            pd.dismiss();

        }else if (password.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            password.setFocusable(true);
            pd.dismiss();

        } else if (alamat.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "alamat Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            alamat.setFocusable(true);
            pd.dismiss();

        } else{
            StringRequest senddata = new StringRequest(Request.Method.POST, ServerAccess.REGISTER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.cancel();
                    try {
                        Log.d("pesan", response);
                        JSONObject res = new JSONObject(response);
                        Toast.makeText(Sign_Up.this, res.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), Sign_In.class);
                        startActivity(intent);
                        pd.dismiss();

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
                    params.put("nik", nik.getText().toString());
                    params.put("nama", nama.getText().toString());
                    params.put("alamat", alamat.getText().toString());
                    params.put("username", username.getText().toString());
                    params.put("password", password.getText().toString());

                    return params;
                }
            };

            AppController.getInstance().addToRequestQueue(senddata);
        }
    }
}
