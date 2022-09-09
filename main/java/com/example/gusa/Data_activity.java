package com.example.gusa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;

import java.util.ArrayList;

public class Data_activity extends Activity {
    public static final String EXTRA_VALUE = "test";
    private Dbhelper db;
    private ListView lv;
    private ArrayList<String>result_string;
    private ArrayList<Entities>entities_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        db = new Dbhelper(this);
        Intent intent = getIntent();
        String t = intent.getStringExtra(EXTRA_VALUE);
        onQuery(t);


    }
    @SuppressLint("SetTextI18n")
    private void onQuery(String value){
        ListView lv = (ListView)findViewById(R.id.result_list);
        try {
        SQLiteDatabase data = db.getReadableDatabase();
        Entities entitie;
        entities_list = new ArrayList<>();
        Cursor cur = data.query("RECLAMOS",
                new String[]{"_id","REF","DATA"}, "REF = ? OR DATA = ?", new String[]{value},
                null, null, null);
        if (cur.moveToFirst()&& cur.getCount()!=0){
            entitie = new Entities();
            entitie.setId(cur.getInt(0));
            entitie.setRef(cur.getString(1));
            entitie.setDate(cur.getString(2));
            entities_list.add(entitie);
            result_string = new ArrayList<String>();
            for (int i=0; i<entities_list.size();i++){
                result_string.add(entities_list.get(i).getId()+"|"+" "+"REF"+" "
                        +entities_list.get(i).getRef()+"|"+" "+"SOLVED SINCE"+" "+entities_list.get(i).getdate());
            }
            lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, result_string));
            lv.setSelected(true);
        }
        cur.close();
        db.close();
    } catch (
    SQLiteException err) {
            Toast toast = Toast.makeText(this, "No disponible", Toast.LENGTH_LONG);
            toast.show();
        }

    }


}