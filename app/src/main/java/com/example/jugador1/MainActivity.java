package com.example.jugador1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, onMessageListener,View.OnTouchListener{

    private Button shoot;
    private Button right;
    private Button up;
    private Button down;
    private Button left;
    private TCPSingleton tcp;
    private int x;
    private int y;
    public String dirIP;
    public boolean presionado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tcp = TCPSingleton.getInstance();
        dirIP = getSharedPreferences("ip", MODE_PRIVATE).getString("dirIP", "NO_IP");
        tcp.setDirIP(dirIP);
        tcp.start();
        //referencias
        shoot = findViewById(R.id.shoot);
        right = findViewById(R.id.right);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        left = findViewById(R.id.left);

        x = 0;
        y = 0;


        //clicks
        /*
        shoot.setOnClickListener(this);
        right.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);

         */


        shoot.setOnClickListener(this);
        //clicks ontouch
        right.setOnTouchListener(this);
        up.setOnTouchListener(this);
        down.setOnTouchListener(this);
        left.setOnTouchListener(this);

    }

//movimeinto con ontouch
public boolean onTouch(View view, MotionEvent event) {
switch (event.getAction()){
    case MotionEvent.ACTION_DOWN:
        presionado = true;
        break;
    case MotionEvent.ACTION_UP:
        presionado = false;
        break;
    case MotionEvent.ACTION_MOVE:
        Log.e("---->",event.getX()+","+event.getY());
        break;
}
if(presionado == true){
    new Thread(
            ()->{
                while(presionado){
                    switch (view.getId()){
                        case R.id.right:
                            Log.e(">>", "right");
                            x += 5;
                            break;
                        case R.id.up:
                            Log.e("^^", "up");
                            y -= 5;
                            break;
                        case R.id.down:
                            Log.e("ll", "down");
                            y += 5;
                            break;
                        case R.id.left:
                            Log.e("<<", "left");
                            x -= 5;
                            break;

                    }
                    Coordenada coor = new Coordenada(x,y);
                    Gson gson = new Gson();
                    String json = gson.toJson(coor);
                    tcp.sendMessage(json);

                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
    ).start();
}

    return false;
}

    //movimiento con onclic
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shoot:
                Log.e("--", "shoot");
                Disparo disparo = new Disparo(5, x, y);
                break;
        }

        
        Coordenada coor = new Coordenada(x,y);
        Gson gson = new Gson();
        String json = gson.toJson(coor);
        tcp.sendMessage(json);

    }
/*
    //movimiento con onclic
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

 */



    @Override
    public void onMessage(String msg) {

    }

}