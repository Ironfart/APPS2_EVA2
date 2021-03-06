package com.example.eva2_8_sqlite3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendAdapter extends ArrayAdapter<Friend> {

    private Context context;
    private int resource;
    ArrayList<Friend> amigo;

    public FriendAdapter(Context context, int resource, ArrayList<Friend> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.amigo = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtId, txtName, txtPhone;

        if(convertView == null){
            LayoutInflater iInflator = ((Activity)context).getLayoutInflater();
            convertView = iInflator.inflate(resource,parent,false);
        }

        //txtId = convertView.findViewById(R.id.txtVwId);
        txtName = convertView.findViewById(R.id.txtVwName);
        txtPhone = convertView.findViewById(R.id.txtVwPhone);

        //txtId.setText(amigo.get(position).getId());
        txtName.setText(amigo.get(position).getName());
        txtPhone.setText(amigo.get(position).getPhone());


        return convertView;
    }
}
