package com.example.eva2_5_files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText txtData;

    final String ARCHIVO = "xxx.txt";
    String sRutaSD;
    final int PERMISO_ESCRITURA = 1000;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData = findViewById(R.id.edTxtData);

        sRutaSD = getExternalFilesDir(null).getPath(); //Environment.getExternalStorageDirectory().getAbsolutePath();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISO_ESCRITURA);

        }
    }

    public void onRead(View v){
        try {
            //InputStream is = openFileInput(ARCHIVO);
            File file = new File(getExternalFilesDir(null), ARCHIVO);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);

            String string;
            while ((string = br.readLine())!= null){
                txtData.append(string + "\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onWrite(View v) {
        String string = txtData.getText().toString();

        try {
            File file = new File(getExternalFilesDir(null), ARCHIVO);
            //FileOutputStream fos=new FileOutputStream(sRutaSD);
            FileOutputStream fos = new FileOutputStream(file);
            //OutputStream os = openFileOutput(ARCHIVO, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(string);
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
