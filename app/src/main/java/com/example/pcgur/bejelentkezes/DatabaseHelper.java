package com.example.pcgur.bejelentkezes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pcgur on 2018. 02. 04..
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // ADATBÁZIS FELÉPÍTÉSE

    public static final String DATABASE_NAME = "Felhasznalo.db";     //ADATBÁZIS FILE NEVE
    public static final String TABLE_NAME = "Felhasznalo_tabla";     //TÁBLA NEVE

    public static final String COL_1 = "ID";                    //ELSŐ OSZLOP NEVE
    public static final String COL_2 = "FELHASZNALONEV";            //MÁSODIK OSZLOP NEVE
    public static final String COL_3 = "JELSZO";            //HARMADIK OSZLOP NEVE
    public static final String COL_4 = "TELJESNEV";                  //NEGYEDIK OSZLOP NEVE

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    //LÉTREHOZZUK A TÁBLÁT ÉS A BENNE LÉVŐ OSZLOPOKHOZ TÍPUST ADUNK

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FELHASZNALONEV TEXT, JELSZO TEXT, TELJESNEV TEXT)");
    }

    //DOBJA EL A TÁBLÁT HA MÁR ILYEN LÉTEZIK

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    //ADAT FELVÉTEL

    public boolean adatRogzites(String felhasznalonev, String jelszo, String teljesnev){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,felhasznalonev);
        contentValues.put(COL_3,jelszo);
        contentValues.put(COL_4,teljesnev);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;       //sikertelen felvétel esetén false eredményt add vissza
        }else{
            return true;       //sikeres felvétel esetén true eredményt add vissza
        }
    }

    //ADAT LEKÉRDEZÉS

    public Cursor adatLekerdezes()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME,null);
        return res;
    }

    //ADAT TÖRLÉS

    public Integer adatTorles(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME, "ID=?", new String[]{id});
        return i;
    }
}
