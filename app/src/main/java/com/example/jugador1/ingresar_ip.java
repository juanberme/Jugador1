package com.example.jugador1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ingresar_ip extends AppCompatActivity {

    private EditText dirIP;
    private Button jugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_ip);

        //referencias
        dirIP = findViewById(R.id.dirIP);
        jugar = findViewById(R.id.jugar);

        jugar.setOnClickListener(
                (view)->{
                    String ip = dirIP.getText().toString();
                    SharedPreferences preferences = getSharedPreferences("ip", MODE_PRIVATE);
                    preferences.edit().putString("dirIP", ip).apply();
                    Intent a = new Intent(this, MainActivity.class);
                        startActivity(a);
                }
        );
    }
}