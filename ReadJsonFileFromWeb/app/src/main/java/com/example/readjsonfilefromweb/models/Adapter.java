package com.example.readjsonfilefromweb.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.readjsonfilefromweb.R;
import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context cnt;
    private int layout;
    private ArrayList<User> list;

    public Adapter(Context cnt, int layout, ArrayList<User> list) {
        this.cnt = cnt;
        this.layout = layout;
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

    class ViewHolder{
        TextView name,address,phone;
        Button btnWeb;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.name = convertView.findViewById(R.id.txtname);
            holder.address = convertView.findViewById(R.id.txtAddress);
            holder.phone = convertView.findViewById(R.id.txtPhone);
            holder.btnWeb = convertView.findViewById(R.id.btnWebsite);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress().getStreet());
        holder.phone.setText(list.get(position).getPhone());
        holder.btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www."+list.get(position).getWebsite()));
                cnt.startActivity(intent);
            }
        });

        return convertView;
    }
}
