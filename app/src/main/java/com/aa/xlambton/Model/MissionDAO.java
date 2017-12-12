package com.aa.xlambton.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Amanda on 11/12/2017.
 */

public class MissionDAO extends SQLiteOpenHelper {

    public MissionDAO(Context context) {
        super(context, "c0719157c0712150DB3", null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS Mission (id INTEGER PRIMARY KEY, name TEXT, date DATE, status TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Mission";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(Mission mission) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues missionData = new ContentValues();
        //agentData.put("description", product.getDescription());


        db.insert("Mission", null, missionData);
    }

    public List<Mission> dbSearch() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Mission;";

        Cursor c = db.rawQuery(sql, null);
        List<Mission> missionList = new ArrayList<Mission>();

        while (c.moveToNext()) {
            Mission mission = new Mission();

            mission.setId(c.getInt(c.getColumnIndex("id")));
            mission.setName(c.getString(c.getColumnIndex("name")));
            //mission.setDate(c.getDate(c.getColumnIndex("date")));
            mission.setStatus(c.getString(c.getColumnIndex("status")));

            missionList.add(mission);
        }
        c.close();

        return missionList;
    }
}
