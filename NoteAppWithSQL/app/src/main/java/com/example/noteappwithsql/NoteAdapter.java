package com.example.noteappwithsql;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    private MainActivity cnt;
    private int layout;
    private List<Note> noteList;

    public NoteAdapter(MainActivity cnt, int layout, List<Note> noteList) {
        this.cnt = cnt;
        this.layout = layout;
        this.noteList = noteList;
    }

    private class ViewHolder{
        TextView txt;
        ImageView imgDl, imgEd;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view ==null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.kind_of_note,null);
            holder.txt = (TextView) view.findViewById(R.id.txt);
            holder.imgDl = (ImageView) view.findViewById(R.id.icD);
            holder.imgEd = (ImageView) view.findViewById(R.id.icE);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        Note note = noteList.get(i);
        holder.txt.setText(note.getName());
        holder.imgEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt.FormEdit(note.getId());
            }
        });
        holder.imgDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt.FormDelete(note.getId());
            }
        });
        return view;
    }
}
