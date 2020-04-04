package com.example.eva2_7_sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Secundaria extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SQLiteDatabase db;

    ListView lstClima;
    Friend[] friend = {};

    ArrayList<Friend> friends = new ArrayList<>(Arrays.asList(friend));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        db = this.openOrCreateDatabase("myfriendsDB",MODE_PRIVATE, null);

        String sql = "select * from tblAmigo";
        Cursor c1 = db.rawQuery(sql, null);
        c1.moveToPosition(-1);
        while ( c1.moveToNext() ){
            int recId = c1.getInt(0);
            String name = c1.getString(1);
            String phone = c1.getString(c1.getColumnIndex("phone"));
            // do something with the record here...
            friends.add(new Friend(name, phone));
        }

        lstClima = findViewById(R.id.lstFriend);
        lstClima.setAdapter(new FriendAdapter(this, R.layout.friend_list, friends));
        lstClima.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
