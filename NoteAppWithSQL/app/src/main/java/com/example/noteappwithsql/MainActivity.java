package com.example.noteappwithsql;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Application;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    NoteAdapter adapter;
    List<Note> note;
    DataBase dataBase;
    Button btn;
    Button btnCofirm;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        btn = (Button) findViewById(R.id.btnAdd);

        note = new ArrayList<>();
        dataBase = new DataBase(this, "tbNote.sqlite",null,1);
        dataBase.QueryData("create table if not exists CongViec (id integer primary key autoincrement," +
                "name varchar(200))");
        //dataBase.QueryData("insert into CongViec values(null,'Rửa bát')");
        //dataBase.QueryData("insert into CongViec values(null,'Quét nhà')");

        popData();
        adapter = new NoteAdapter(MainActivity.this, R.layout.kind_of_note,note);
        listView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void popData(){
        Cursor data = dataBase.GetData("select * from CongViec");
        note.clear();
        while (data.moveToNext()){
            int id = data.getInt(0);
            String name = data.getString(1);
//            Toast.makeText(this, id+" "+name, Toast.LENGTH_LONG).show();
            note.add(new Note(id,name));
        }
    }

    public  void FormEdit(int id){
        Dialog dal = new Dialog(this);
        dal.setContentView(R.layout.form_edit_work);
        EditText editText = (EditText) dal.findViewById(R.id.edtUpdate);
        Button btnE = (Button) dal.findViewById(R.id.btnUpdate);
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                if(data.length()!=0) {
                    String query = "update CongViec set name = '" + data + "' where id = " + id;
                    dataBase.QueryData(query);
                    popData();
                    adapter.notifyDataSetChanged();
                    dal.dismiss();
                }
            }
        });
        dal.show();

    }
    private void Form(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.form_add_work);
        dialog.show();

        btnCofirm =(Button) dialog.findViewById(R.id.btnCofirm);
        edt = (EditText) dialog.findViewById(R.id.editText);
        btnCofirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edt.getText().toString();
                if(s.length()!=0){
                    String query = "insert into CongViec values(null,'"+s+"')";
                    dataBase.QueryData(query);
                    popData();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
    }

    public void FormDelete(int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.form_delete_work);
        Button btnOk = (Button) dialog.findViewById(R.id.btnOkDelete);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancelDelete);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "delete from CongViec where id = "+id;
                dataBase.QueryData(str);
                popData();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}