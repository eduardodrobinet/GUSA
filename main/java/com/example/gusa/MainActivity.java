package com.example.gusa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity<Private> extends AppCompatActivity implements View.OnClickListener {
    private TextView reference_number;
    private Dbhelper mydb;
    //private DBManager dm;
    private Cursor c;
    private String val1;
    Button query, insert;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CONSULTAR RECLAMO");
        setContentView(R.layout.activity_main);
        String val1;
        mydb = new Dbhelper(this);
        query = findViewById(R.id.button);
        insert = findViewById(R.id.button1);
        query.setOnClickListener(this);
        insert.setOnClickListener(this);

    }
    @SuppressLint({"NonConstantResourceId", "Recycle", "SetTextI18n"})
    @Override
     public void onClick(View view) {
        final TextView infoView = (TextView) findViewById(R.id.reclaim_input);
        final EditText reference_number = (EditText) findViewById(R.id.numberinput);
        final String a = reference_number.getText().toString();
        Intent intent = new Intent(this, Data_activity.class);
        intent.putExtra("test", a);
        startActivity(intent);






        /* try {
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor cur = db.query("RECLAMOS",
                    new String[]{"_id", "REF","DATA"}, "REF = ?",
                    new String[]{a}, null, null, null);
            if (cur.moveToFirst()&&cur.getCount()!=0){
                String ref = cur.getString(1);
                String data = cur.getString(2);
                message(ref+"\n"+"resuelto desde"+"\n"+data);
            }else {
                message("No resuelto");
            }
            cur.close();
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "No disponible", Toast.LENGTH_LONG);
            toast.show();
        }*/
    }
    public void insert(String val){
        DateFormat current = DateFormat.getTimeInstance(DateFormat.LONG);
        SQLiteDatabase database = mydb.getWritableDatabase();
        ContentValues reference = new ContentValues();
        reference.put("REF", val);
        reference.put("DATA", String.valueOf(current));
        database.insert("RECLAMOS", null, reference);
        database.close();
    }


    public void alertMistake(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("Error");
    }
    public void message(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Info")
                .setCancelable(true)
                .show();

    }




}