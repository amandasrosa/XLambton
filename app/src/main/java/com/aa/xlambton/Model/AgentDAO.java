package com.aa.xlambton.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
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
        String sql = "CREATE TABLE IF NOT EXISTS Agent (id INTEGER PRIMARY KEY, name TEXT, level TEXT, agency TEXT, " +
                "website TEXT, country TEXT, phoneNumber TEXT, address TEXT, photo BLOB)";
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

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        agent.getPhoto().compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bytes = bos.toByteArray();

        ContentValues agentData = new ContentValues();
        agentData.put("name", agent.getName());
        agentData.put("agency", agent.getAgency());
        agentData.put("website", agent.getWebsite());
        agentData.put("country", agent.getCountry());
        agentData.put("phoneNumber", agent.getPhoneNumber());
        agentData.put("address", agent.getAddress());
        agentData.put("photo", bytes);

        db.insert("Agent", null, agentData);
    }

    public List<Agent> dbSearch() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Agent;";

        Cursor c = db.rawQuery(sql, null);
        List<Agent> agentList = new ArrayList<Agent>();

        while (c.moveToNext()) {
            Agent agent = new Agent();

            agent.setId(c.getLong(c.getColumnIndex("id")));
            agent.setName(c.getString(c.getColumnIndex("name")));
            agent.setLevel(c.getString(c.getColumnIndex("level")));
            agent.setAgency(c.getString(c.getColumnIndex("agency")));
            agent.setWebsite(c.getString(c.getColumnIndex("website")));
            agent.setCountry(c.getString(c.getColumnIndex("country")));
            agent.setPhoneNumber(c.getString(c.getColumnIndex("phoneNumber")));
            agent.setAddress(c.getString(c.getColumnIndex("address")));
            byte[] bytes = c.getBlob(c.getColumnIndex("photo"));
            agent.setPhoto(BitmapFactory.decodeByteArray(bytes, 0 , bytes.length));

            agentList.add(agent);
        }
        c.close();

        return agentList;
    }

    public Agent dbSearchById (Long id) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Agent;";

        Cursor c = db.rawQuery(sql, null);

        Agent agent = new Agent();
        while (c.moveToNext()) {

            if (c.getLong(c.getColumnIndex("id")) == id) {
                agent.setId(c.getLong(c.getColumnIndex("id")));
                agent.setName(c.getString(c.getColumnIndex("name")));
                agent.setLevel(c.getString(c.getColumnIndex("level")));
                agent.setAgency(c.getString(c.getColumnIndex("agency")));
                agent.setWebsite(c.getString(c.getColumnIndex("website")));
                agent.setCountry(c.getString(c.getColumnIndex("country")));
                agent.setPhoneNumber(c.getString(c.getColumnIndex("phoneNumber")));
                agent.setAddress(c.getString(c.getColumnIndex("address")));
                byte[] bytes = c.getBlob(c.getColumnIndex("photo"));
                agent.setPhoto(BitmapFactory.decodeByteArray(bytes, 0 , bytes.length));
            }
        }
        c.close();
        return agent;
    }

    public List<Agent> dbSearchByName (String name) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Agent;";

        Cursor c = db.rawQuery(sql, null);
        List<Agent> agentList = new ArrayList<>();

        while (c.moveToNext()) {
            Agent agent = new Agent();

            if (c.getString(c.getColumnIndex("name")).contains(name)) {
                agent.setId(c.getLong(c.getColumnIndex("id")));
                agent.setName(c.getString(c.getColumnIndex("name")));
                agent.setLevel(c.getString(c.getColumnIndex("level")));
                agent.setAgency(c.getString(c.getColumnIndex("agency")));
                agent.setWebsite(c.getString(c.getColumnIndex("website")));
                agent.setCountry(c.getString(c.getColumnIndex("country")));
                agent.setPhoneNumber(c.getString(c.getColumnIndex("phoneNumber")));
                agent.setAddress(c.getString(c.getColumnIndex("address")));
                byte[] bytes = c.getBlob(c.getColumnIndex("photo"));
                agent.setPhoto(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                agentList.add(agent);
            }
        }
        c.close();
        return agentList;
    }

    public void dbDelete (Agent agent) {
        SQLiteDatabase db = getWritableDatabase();

        String[] param = {agent.getId().toString()};
        db.delete("Agent", "id = ?", param);
    }

}
