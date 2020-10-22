package com.example.jugador1;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPSingleton extends Thread {

    private static TCPSingleton unico;

    public static TCPSingleton getInstance() {
        if(unico == null) {
            unico = new TCPSingleton();
            unico.start();
        }
        return unico;
    }

    private TCPSingleton(){}

    private Socket cliente;
    private BufferedReader reader;
    private BufferedWriter writer;
    private onMessageListener observer;

    public void setObserver(onMessageListener observer) {
        this.observer = observer;
    }

    public void run() {

            Log.e(">>", "Waiting server");
        try {

            cliente = new Socket("192.168.1.10",5000);
            System.out.println("Successfully connected");

            InputStream is = cliente.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));

            OutputStream os = cliente.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(os));

            while(true) {
                String linea = reader.readLine();
                //System.out.println(linea);
                observer.onMessage(linea);
                Log.e(">>>", linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        new Thread(
                ()->{
                    try {
                        writer.write(msg+"\n");
                        writer.flush();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }

}
