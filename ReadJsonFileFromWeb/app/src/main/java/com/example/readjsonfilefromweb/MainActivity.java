package com.example.readjsonfilefromweb;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readjsonfilefromweb.apis.ApiService;
import com.example.readjsonfilefromweb.models.Fruit;
import com.example.readjsonfilefromweb.models.User;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    User user;

    TextView txtname,txtcity,txtcountry;
    Button btnSingle,btnList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtname = findViewById(R.id.txtName);
        txtcity = findViewById(R.id.txtCity);
        txtcountry = findViewById(R.id.txtCountry);
        btnSingle = findViewById(R.id.btnCallApiSingle);
        btnList = findViewById(R.id.btnCallApiList);


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
                callApiList();
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
    public void callApiList(){
        ApiService.apiServiceList.convertObjectList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();

                List<User> users = response.body();
                for (User user:users){
                    Log.i("APIIIIIIIIIII",
                            user.getId()+" "+
                            user.getName()+" "+
                            user.getUsername()+" "+
                            user.getEmail()+" "+
                            user.getAddress().getStreet()+" "+
                            user.getAddress().getSuite()+" "+
                            user.getAddress().getCity()+" "+
                            user.getAddress().getZipcode()+" "+
                            user.getAddress().getGeo().getLat()+" "+
                            user.getAddress().getGeo().getLng()+" "+
                            user.getPhone()+" "+
                            user.getWebsite()+" "+
                            user.getCompany().getName()+" "+
                            user.getCompany().getCatchPhrase()+" "+
                            user.getCompany().getBs()
                            );
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "!ok", Toast.LENGTH_SHORT).show();
            }
        });

    }


}