package com.aa.xlambton.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Amanda on 11/12/2017.
 */

public class UserDAO extends SQLiteOpenHelper {

    public UserDAO(Context context) {
        super(context, "c0719157c0712150DB", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS User (id INTEGER PRIMARY KEY, username TEXT NOT NULL, password TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS User";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(User user) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues userData = new ContentValues();
        userData.put("username", user.getUsername());
        userData.put("password", user.getPassword());

        db.insert("User", null, userData);
    }

    public boolean checkUserNPass (String username, String password) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "SELECT email, password FROM User";

        Cursor c = db.rawQuery(sql, null);

        boolean returnBool = false;

        while (c.moveToNext()) {
            String usernameDb = c.getString(0);
            String passwordDb = c.getString(1);

            if (usernameDb.equals(username)) {
                if (passwordDb.equals(password)) {
                    returnBool = true;
                    break;
                } else {
                    returnBool = false;
                }
            } else {
                returnBool = false;
            }
        }

        return returnBool;
    }
}
