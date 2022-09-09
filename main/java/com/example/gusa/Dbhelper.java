package com.example.gusa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {

    // Database information //
    static final String DB_NAME = "RECLAMOS_GUSA";
    static final int DB_VERSION = 3;

    public Dbhelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        if (DB_VERSION <3){
            updateDatabase(db);
        }


    }

    private void updateDatabase(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RECLAMOS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "REF TEXT NOT NULL, "
                + "DIRECCION TEXT, "
                + "DESCRIPCION TEXT,"
                +"DATA DATETIME);");
        db.execSQL("CREATE UNIQUE INDEX idx_ref ON RECLAMOS(REF);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + "\n"+"RECLAMOS");
        onCreate(sqLiteDatabase);
    }

}

