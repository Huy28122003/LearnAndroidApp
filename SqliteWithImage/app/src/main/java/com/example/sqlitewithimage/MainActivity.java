package com.example.sqlitewithimage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBase dataBase;
    Button btnAdd;
    ListView productList;

    ArrayList<Product> products = new ArrayList<>();
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        productList = findViewById(R.id.listView);


        dataBase = new DataBase(this, "Product.sqlite",null,1);
//        dataBase.setData("drop table if exists products");
        dataBase.setData("create table if not exists products (id integer primary key autoincrement, name text, status text, price double, image blob)");

        popData();

        productAdapter = new ProductAdapter(this,R.layout.product_item,products);
        productList.setAdapter(productAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProductAddActivity.class));
            }
        });
    }

    public void popData(){
        Cursor cursor = dataBase.getData("select * from products");
        products.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String status = cursor.getString(2);
            double price = cursor.getDouble(3);
            byte[] image = cursor.getBlob(4);
            products.add(new Product(id,name,status,price,image));
            Log.d("aaaaaaaaa",id+" "+name+" "+status+" "+price+" "+image.toString());
        }
        //productAdapter.notifyDataSetChanged();
        Toast.makeText(this, products.size()+"", Toast.LENGTH_SHORT).show();
        cursor.close();
    }
}