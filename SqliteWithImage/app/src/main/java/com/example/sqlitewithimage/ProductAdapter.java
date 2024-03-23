package com.example.sqlitewithimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Product> products;

    public ProductAdapter(Context context, int layout, ArrayList<Product> products) {
        this.context = context;
        this.layout = layout;
        this.products = products;
    }

    class ViewHolder{
        TextView name,status,price;
        ImageView image;
    }
    @Override
    public int getCount() {
        return products.size();
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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_item,null);
            holder.name = convertView.findViewById(R.id.txtName);
            holder.status = convertView.findViewById(R.id.txtStatus);
            holder.price = convertView.findViewById(R.id.txtPrice);
            holder.image = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }

        holder.name.setText(products.get(position).getName());
        holder.status.setText(products.get(position).getStatus());
        holder.price.setText(products.get(position).getPrice()+"");

        byte[] image = products.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        holder.image.setImageBitmap(bitmap);
        return convertView;
    }
}
