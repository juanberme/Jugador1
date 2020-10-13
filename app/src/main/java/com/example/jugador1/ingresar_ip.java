package com.example.jugador1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                    String ip = jugar.getText().toString();
                    Intent a = new Intent(this, MainActivity.class);
                        startActivity(a);
                }
        );
    }
}