package com.example.readjsonfilefromweb.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readjsonfilefromweb.R;
import com.example.readjsonfilefromweb.apis.ApiService;
import com.example.readjsonfilefromweb.models.Fruit;
import com.example.readjsonfilefromweb.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtname,txtcity,txtcountry;
    Button btnSingle,btnList,btnLink1,btnLink2;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtname = findViewById(R.id.txtName);
        txtcity = findViewById(R.id.txtCity);
        txtcountry = findViewById(R.id.txtCountry);
        btnSingle = findViewById(R.id.btnCallApiSingle);
        btnList = findViewById(R.id.btnCallApiList);
        btnLink1 = findViewById(R.id.btnRS1);
        btnLink2 = findViewById(R.id.btnRS2);


//        user = new User("Huy","Thai Binh","Viet Nam");
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        Log.i("aaaaaaaaaaa",json);

        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApiSingle();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ObjectListActivity.class));
            }
        });

        btnLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://filesamples.com/samples/code/json/sample1.json"));
                startActivity(intent);
            }
        });

        btnLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://jsonplaceholder.typicode.com/users"));
                startActivity(intent);
            }
        });

    }
    public void callApiSingle(){
        ApiService.apiService.convertObjectSingle().enqueue(new Callback<Fruit>() {
            @Override
            public void onResponse(Call<Fruit> call, Response<Fruit> response) {
                Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                Fruit fruit = response.body();
                txtname.setText(fruit.getFruit());
                txtcity.setText(fruit.getSize());
                txtcountry.setText(fruit.getColor());
            }

            @Override
            public void onFailure(Call<Fruit> call, Throwable t) {
                Toast.makeText(MainActivity.this, "!ok", Toast.LENGTH_SHORT).show();
            }
        });


    }



}