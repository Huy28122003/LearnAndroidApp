package com.example.readjsonfilefromweb.process;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.readjsonfilefromweb.R;
import com.example.readjsonfilefromweb.apis.ApiService;
import com.example.readjsonfilefromweb.models.Opinion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity {

    EditText userId,id,title,body;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        userId = findViewById(R.id.userId);
        id = findViewById(R.id.id);
        title = findViewById(R.id.usertitle);
        body = findViewById(R.id.body);
        btnSend = findViewById(R.id.btnSend);


    }

    private void sendPost() {

//        int uI = Integer.parseInt(userId.getText().toString());
//        String id = this.id.getText().toString();
//        String title = this.title.getText().toString();
//        String body = this.body.getText().toString();

        Opinion opinion = new Opinion(11,101,"title","body");

        ApiService.apiPost.sendPosts(opinion).enqueue(new Callback<Opinion>() {
            @Override
            public void onResponse(Call<Opinion> call, Response<Opinion> response) {

            }

            @Override
            public void onFailure(Call<Opinion> call, Throwable t) {

            }
        });

    }
}