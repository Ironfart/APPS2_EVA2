package com.example.eva2_8_sqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SQLiteDatabase db;

    ListView lstClima;

    final Context context = this;

    Friend[] friend = {};

    ArrayList<Friend> friends = new ArrayList<>(Arrays.asList(friend));
    FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = this.openOrCreateDatabase("myfriendsDB", MODE_PRIVATE, null);

        db.execSQL("create table if not exists tblAMIGO ("
                + " recID integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " phone text ); " );

        /*db.execSQL("insert into tblAMIGO(name, phone) values ('AAA', '555-1234' );");
        db.execSQL("insert into tblAMIGO(name, phone) values ('BBB', '666-5678' );");
        db.execSQL("insert into tblAMIGO(name, phone) values ('CCC', '777-7890' );");*/

        lstClima = findViewById(R.id.lstFriend);

        adapter = new FriendAdapter(this, R.layout.friend_list, friends);

        lstClima.setAdapter(adapter);
        lstClima.setOnItemClickListener(this);

        refreshDB();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // custom dialog
        final Friend f = friends.get(i);
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.record_popup);
        dialog.setTitle(f.getName());

        // set the custom dialog components - text, image and button
        TextView txtName, txtPhone;
        final EditText edTxtName, edTxtPhone;

        Button btnDel = dialog.findViewById(R.id.btnDel);
        Button btnUpd = dialog.findViewById(R.id.btnUpd);

        edTxtName = dialog.findViewById(R.id.edTxtUName);
        edTxtPhone = dialog.findViewById(R.id.edTxtUPhone);

        txtName = dialog.findViewById(R.id.txtVwName);
        txtPhone = dialog.findViewById(R.id.txtVwPhone);

        txtName.setText(f.getName().toString());
        txtPhone.setText(f.getPhone().toString());

        // if button is clicked, close the custom dialog
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] whereArgs = { f.getId()+"" };

                int recAffected = db.delete("tblAMIGO", "recID = ?", whereArgs);

                Toast.makeText(context, "Registro eliminado", Toast.LENGTH_SHORT).show();

                refreshDB();
                dialog.dismiss();
            }
        });

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, phone;
                String[] whereArgs = { f.getId()+"" };
                ContentValues cv = new ContentValues();

                if (edTxtName.getText().toString().equals("") || edTxtPhone.getText().toString().equals("")){
                    Toast.makeText(context, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    name = edTxtName.getText().toString();
                    phone = edTxtPhone.getText().toString();

                    cv.put("name", name);
                    cv.put("phone", phone);

                    int recAffected = db.update("tblAMIGO", cv, "recID = ?", whereArgs);

                    Toast.makeText(context, "Registro actualizado", Toast.LENGTH_SHORT).show();
                    
                    refreshDB();
                    dialog.dismiss();
                }
            }});

        dialog.show();
    }

    public void onClick(View v) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.insert_record);
        dialog.setTitle("Insertar");

        // set the custom dialog components - text, image and button
        TextView txtName, txtPhone;
        final EditText edTxtName, edTxtPhone;

        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);
        edTxtName = dialog.findViewById(R.id.edTxtName);
        edTxtPhone = dialog.findViewById(R.id.edTxtTel);


        // if button is clicked, close the custom dialog
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, phone;
                ContentValues cv = new ContentValues();

                name = edTxtName.getText().toString();
                phone = edTxtPhone.getText().toString();

                cv.put("name", name);
                cv.put("phone", phone);

                long rowPosition = db.insert("tblAMIGO", null, cv);

                Toast.makeText(MainActivity.this, "Registro insertado", Toast.LENGTH_SHORT).show();

                refreshDB();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void refreshDB(){
        friends.clear();
        adapter.notifyDataSetChanged();
        String sql = "select * from tblAmigo";
        Cursor c1 = db.rawQuery(sql, null);
        c1.moveToPosition(-1);
        while ( c1.moveToNext() ){
            int recId = c1.getInt(0);
            String name = c1.getString(1);
            String phone = c1.getString(c1.getColumnIndex("phone"));
            // do something with the record here...
            friends.add(new Friend(recId, name, phone));
        }

    }

    /*public void onDBSave(View v){
        String name, phone, sql;
        name = edTxtName.getText().toString();
        phone = edTxtPhone.getText().toString();

        sql = "insert into tblAMIGO(name, phone) values ('"+ name + "', '" + phone + "' );";

        db.execSQL( sql );
    }*/

}

