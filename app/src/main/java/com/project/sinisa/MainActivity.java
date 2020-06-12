package com.project.sinisa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.project.sinisa.akun.Sign_In;
import com.project.sinisa.dashboard.Dashboard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        membuat intent ketika waktu sudah habis maka akan diarahkan ke halaman login
        final Intent i = new Intent(this, Sign_In.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
//        memulai timer splash screen
        timer.start();
    }
}
