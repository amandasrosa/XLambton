package com.aa.xlambton.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amanda on 12/12/2017.
 */

public class AgentMissionDAO extends SQLiteOpenHelper {

    public AgentMissionDAO(Context context) {
        super(context, "c0719157c0712150DB6", null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS AgentMission (id INTEGER PRIMARY KEY, agent_id INTEGER, mission_id INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS AgentMission";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(AgentMission agentMission) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues agentMissionData = new ContentValues();
        //agentData.put("description", product.getDescription());


        db.insert("AgentMission", null, agentMissionData);
    }

    public List<AgentMission> dbSearch() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM AgentMission;";

        Cursor c = db.rawQuery(sql, null);
        List<AgentMission> agentMissionList = new ArrayList<AgentMission>();

        while (c.moveToNext()) {
            AgentMission agentMission = new AgentMission();

            agentMission.setId(c.getInt(c.getColumnIndex("id")));
            //agentMission.setAgent(c.getInt(c.getColumnIndex("agent_id")));
            //agentMission.setMission(c.getInt(c.getColumnIndex("mission_id")));

            agentMissionList.add(agentMission);
        }
        c.close();

        return agentMissionList;
    }
}
