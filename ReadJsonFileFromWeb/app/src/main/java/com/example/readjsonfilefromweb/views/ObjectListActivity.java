package com.example.readjsonfilefromweb.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.readjsonfilefromweb.R;
import com.example.readjsonfilefromweb.apis.ApiService;
import com.example.readjsonfilefromweb.models.Adapter;
import com.example.readjsonfilefromweb.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjectListActivity extends AppCompatActivity {
    Adapter adapter;
    ArrayList<User> users;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_list);
        listView= findViewById(R.id.lv);
        users = new ArrayList<>();
        ApiService.apiServiceList.convertObjectList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> data = response.body();
                for (User user:data){
                    users.add(user);
                }
                adapter = new Adapter(ObjectListActivity.this, R.layout.user_item, users);
                listView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ObjectListActivity.this, "!ok", Toast.LENGTH_SHORT).show();
            }
        });


    }
//    public void callApiList(){
//        ApiService.apiServiceList.convertObjectList().enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//
//                List<User> data = response.body();
//                for (User user:data){
//                    users.add(user);
////                    Log.i("APIIIIIIIIIII",
////                            user.getId()+" "+
////                                    user.getName()+" "+
////                                    user.getUsername()+" "+
////                                    user.getEmail()+" "+
////                                    user.getAddress().getStreet()+" "+
////                                    user.getAddress().getSuite()+" "+
////                                    user.getAddress().getCity()+" "+
////                                    user.getAddress().getZipcode()+" "+
////                                    user.getAddress().getGeo().getLat()+" "+
////                                    user.getAddress().getGeo().getLng()+" "+
////                                    user.getPhone()+" "+
////                                    user.getWebsite()+" "+
////                                    user.getCompany().getName()+" "+
////                                    user.getCompany().getCatchPhrase()+" "+
////                                    user.getCompany().getBs()
////                    );
////
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Toast.makeText(ObjectListActivity.this, "!ok", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
}