package com.aa.xlambton.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amanda on 11/12/2017.
 */

public class AgentDAO extends SQLiteOpenHelper {

    public AgentDAO(Context context) {
        super(context, "c0719157c0712150DB2", null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Agent (id INTEGER PRIMARY KEY, name TEXT, level TEXT, agency TEXT, " +
                "website TEXT, country TEXT, phoneNumber TEXT, address TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Agent";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(Agent agent) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues agentData = new ContentValues();
        //agentData.put("description", product.getDescription());


        db.insert("Agent", null, agentData);
    }

    public List<Agent> dbSearch() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Agent;";

        Cursor c = db.rawQuery(sql, null);
        List<Agent> agentList = new ArrayList<Agent>();

        while (c.moveToNext()) {
            Agent agent = new Agent();

            agent.setId(c.getInt(c.getColumnIndex("id")));
            agent.setName(c.getString(c.getColumnIndex("name")));
            agent.setLevel(c.getString(c.getColumnIndex("agency")));
            agent.setWebsite(c.getString(c.getColumnIndex("website")));
            agent.setCountry(c.getString(c.getColumnIndex("country")));
            agent.setPhoneNumber(c.getString(c.getColumnIndex("phoneNumber")));
            agent.setAddress(c.getString(c.getColumnIndex("address")));

            agentList.add(agent);
        }
        c.close();

        return agentList;
    }

}
