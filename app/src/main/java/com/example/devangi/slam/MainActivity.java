package com.example.devangi.slam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private EditText editText_ip, editText_port, editText_message;
    private String ipAddress;
    private int port=0;
    private Socket client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_ip =  findViewById(R.id.ip);
        editText_port =  findViewById(R.id.port);
        editText_message =  findViewById(R.id.msg);

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ipAddress = editText_ip.getText().toString();
                port = Integer.valueOf(editText_port.getText().toString());
                  new  Thread( new Runnable(){
                      @Override
                      public void run(){
                          try {
                              client = new Socket(ipAddress, port);
                              PrintWriter printwriter = new PrintWriter(client.getOutputStream());
                              printwriter.write("this is test message");
                              printwriter.flush();
                              printwriter.close();
                          }
                          catch(Exception e)
                          {
                              e.printStackTrace();
                          }
                      }
                  }).start();
                }
            });


    }
}
