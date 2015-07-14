package com.android.dango.day2ejcolores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dango on 03/07/2015.
 */
public class DataBase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CrownestDB";
    private static final String LOGIN_TABLE_NAME = "login";
    private static final String LOGIN_TABLE_CREATE = "CREATE TABLE " +
            LOGIN_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, mail TEXT, password TEXT)";

    public DataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(LOGIN_TABLE_CREATE);}


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + LOGIN_TABLE_NAME);
        db.execSQL(LOGIN_TABLE_CREATE);
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"mail"};
        Cursor c = db.query(
                LOGIN_TABLE_NAME,  // The table to query
                columns,                                // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );
        return c;
    }


    public Cursor getPasswordByEmail(String userMail) {
        SQLiteDatabase db = this.getWritableDatabase();
        /*Cursor c = db.rawQuery("SELECT * FROM login", null);
        while (c.moveToNext()){
            Log.v("MYDB",c.getString(1));
        }*/

        String[] columns = {"password"};
        String[] where = {userMail};
       Cursor c = db.query(
                LOGIN_TABLE_NAME,  // The table to query
                columns,                                    // The columns to return
                "mail=?",                                   // The columns for the WHERE clause
                where,                                      // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        return c;
    }

    public boolean userMailTaken (String userMail){
        boolean taken = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"mail"};
        String[] where = {userMail};
        Cursor c = db.query(
                LOGIN_TABLE_NAME,  // The table to query
                columns,                                    // The columns to return
                "mail=?",                                   // The columns for the WHERE clause
                where,                                      // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        if (c.moveToFirst()){
            if(userMail.equals(c.getString(c.getColumnIndex("mail"))))
                taken = true;
        }

        return taken;
    }


    public void createUser (ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }

    public void register(SQLiteDatabase db, String email, String pass){
        db.execSQL("INSERT INTO "+LOGIN_TABLE_NAME+" (mail, password) VALUES ('"+email+"', '"+pass+"')");
    }

    public boolean login(SQLiteDatabase db, String email, String pass){
        if(db != null){
            //if(email == db.execSQL("SELECT mail FROM STATISTICS_TABLE_NAME WHERE mail = 'email'"));
            db.close();
        }
        return false;
    }



}
