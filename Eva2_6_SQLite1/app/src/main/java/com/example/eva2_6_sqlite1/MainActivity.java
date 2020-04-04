package com.example.eva2_6_sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onSaveDB(View v){
        SQLiteDatabase db = this.openOrCreateDatabase("myfriendsDB", MODE_PRIVATE, null);
        
        db.execSQL("create table if not exists tblAMIGO ("
            + " recID integer PRIMARY KEY autoincrement, "
            + " name text, "
            + " phone text ); " );
    
        db.execSQL( "insert into tblAMIGO(name, phone) values ('AAA', '555-1111');" );
        db.execSQL( "insert into tblAMIGO(name, phone) values ('BBB', '555-2222');" );
        db.execSQL( "insert into tblAMIGO(name, phone) values ('CCC', '555-3333');" );
    }
}
