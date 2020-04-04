package com.example.eva2_4_filesresources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txtShow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        InputStream is = getResources().openRawResource(R.raw.lorem_ipsum);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String text;

        try {
            while ((text = br.readLine()) != null) {
                txt.append(text+"\n");
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
