package com.example.eva2_7_sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    EditText txtName, txtPhone;

    Intent inLanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.edTxtName);
        txtPhone = findViewById(R.id.edTxtTel);

        inLanzar = new Intent(this, Secundaria.class);

        db = this.openOrCreateDatabase("myfriendsDB", MODE_PRIVATE, null);

        db.execSQL("create table if not exists tblAMIGO ("
            + " recID integer PRIMARY KEY autoincrement, "
            + " name text, "
            + " phone text ); " );
    }

    public void onDBSave(View v){
        String name, phone, sql;
        name = txtName.getText().toString();
        phone = txtPhone.getText().toString();

        sql = "insert into tblAMIGO(name, phone) values ('"+ name + "', '" + phone + "' );";

        db.execSQL( sql );
    }

    public void click(View v){
        startActivity(inLanzar);
    }
}
