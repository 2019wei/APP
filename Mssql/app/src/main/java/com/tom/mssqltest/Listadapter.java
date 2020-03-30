package com.tom.mssqltest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Listadapter extends BaseAdapter {
ArrayList<Data> list = new ArrayList<>();
public Listadapter(ArrayList list){
    this.list = list;
}
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewlayout,parent,false);
            String name =list.get(position).getName();
            String id =list.get(position).getId();
        TextView nameText = convertView.findViewById(R.id.textView1);
        TextView idText = convertView.findViewById(R.id.textView2);
        nameText.setText(name);
        idText.setText(id);
        return convertView;
    }
}
