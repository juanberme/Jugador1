package com.example.jugador1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, onMessageListener{

    private Button shoot;
    private Button right;
    private Button up;
    private Button down;
    private Button left;
    private TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referencias
        shoot = findViewById(R.id.shoot);
        right = findViewById(R.id.right);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        left = findViewById(R.id.left);
        tcp = TCPSingleton.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shoot:
                Log.e("--", "shoot");
                break;
            case R.id.right:
                Log.e(">>", "right");
                break;
            case R.id.up:
                Log.e("^^", "up");
                break;
            case R.id.down:
                Log.e("ll", "down");
                break;
            case R.id.left:
                Log.e("<<", "left");
                break;
        }
    }

    @Override
    public void onMessage(String msg) {

    }
}