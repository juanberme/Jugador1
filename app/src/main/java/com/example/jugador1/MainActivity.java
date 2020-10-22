package com.example.jugador1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, onMessageListener{

    private Button shoot;
    private Button right;
    private Button up;
    private Button down;
    private Button left;
    private TCPSingleton tcp;
    private int x;
    private int y;
    public String dirIP;

    public String getDirIP() {
        return dirIP;
    }

    public void setDirIP(String dirIP) {
        this.dirIP = dirIP;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dirIP = getSharedPreferences("ip", MODE_PRIVATE).getString("dirIP", "NO_IP");

        //referencias
        shoot = findViewById(R.id.shoot);
        right = findViewById(R.id.right);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        left = findViewById(R.id.left);
        tcp = TCPSingleton.getInstance();
        x = 0;
        y = 0;

        //clicks
        shoot.setOnClickListener(this);
        right.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shoot:
                Log.e("--", "shoot");
                Disparo disparo = new Disparo(5, x, y);
                break;

            case R.id.right:
                Log.e(">>", "right");
                x += 10;
                break;

            case R.id.up:
                Log.e("^^", "up");
                y -= 10;
                break;

            case R.id.down:
                Log.e("ll", "down");
                y += 10;
                break;

            case R.id.left:
                Log.e("<<", "left");
                x -= 10;
                break;
        }
        Coordenada coor = new Coordenada(x,y);
        Gson gson = new Gson();
        String json = gson.toJson(coor);
        tcp.sendMessage(json);
    }

    @Override
    public void onMessage(String msg) {

    }

}