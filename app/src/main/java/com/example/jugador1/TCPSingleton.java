package com.example.jugador1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPSingleton extends Thread{

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

            Log.e("--->","Waiting server...");
        try {

            //cliente = new Socket("10.0.2.2",5000);
            //el de bermi
            //cliente = new Socket("192.168.1.10",5000);
            //el de Laura
            cliente = new Socket("192.168.0.3",5000);

            Log.e("--->","Successfully connected");

            InputStream is = cliente.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));

            OutputStream os = cliente.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(os));

            while(true) {
                String linea = reader.readLine();
                Log.e("--",linea);
                observer.onMessage(linea);

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

                        e.printStackTrace();
                    }
                }
        ).start();
    }

}
