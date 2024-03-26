package com.example.readjsonfilefromweb.process;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readjsonfilefromweb.R;
import com.example.readjsonfilefromweb.apis.ApiService;
import com.example.readjsonfilefromweb.models.Animal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity {

    EditText id,name,longevity,env,ate;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        id = findViewById(R.id.animalId);
        name= findViewById(R.id.animalName);
        longevity = findViewById(R.id.animalLong);
        env = findViewById(R.id.env);
        ate = findViewById(R.id.ate);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPost();
            }
        });


    }

    private void sendPost() {

//        int uI = Integer.parseInt(userId.getText().toString());
//        String id = this.id.getText().toString();
//        String title = this.title.getText().toString();
//        String body = this.body.getText().toString();

        Animal animal = new Animal(8,"ant",1,"land","plants");

        ApiService.apiPost.sendPosts(animal).enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                Animal animal = response.body();
                if(animal!=null){
                    Log.i("Postttttttttttt",animal.toString());
                }
                Toast.makeText(PostsActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                Toast.makeText(PostsActivity.this, "!ok", Toast.LENGTH_SHORT).show();

            }
        });

    }
}