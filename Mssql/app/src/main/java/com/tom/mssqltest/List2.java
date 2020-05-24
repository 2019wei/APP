package com.tom.mssqltest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tom.mssqltest.data.Expense;

import java.util.ArrayList;
import java.util.List;

public class List2 extends BaseAdapter {
    List<Expense> list = new ArrayList<>();

    public List2(List list) {
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
        String date = list.get(position).getDate();
        String name = list.get(position).getInfo();
        TextView dateText = convertView.findViewById(R.id.textView1);
        TextView nameText = convertView.findViewById(R.id.textView2);
        dateText.setText(date);
        nameText.setText(name);

        return convertView;
    }
}
