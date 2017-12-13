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
        agentMissionData.put("agent_id", agentMission.getAgent().getId());
        agentMissionData.put("mission_id", agentMission.getMission().getId());

        db.insert("AgentMission", null, agentMissionData);
    }

    public List<AgentMission> dbSearch(Context context) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM AgentMission;";

        Cursor c = db.rawQuery(sql, null);
        List<AgentMission> agentMissionList = new ArrayList<AgentMission>();

        while (c.moveToNext()) {
            AgentMission agentMission = new AgentMission();

            AgentDAO daoA = new AgentDAO(context);
            Long agentId = c.getLong(c.getColumnIndex("agent_id"));
            Agent agent = daoA.dbSearchById(agentId);
            daoA.close();
            MissionDAO daoM = new MissionDAO(context);
            Long missionId = c.getLong(c.getColumnIndex("mission_id"));
            Mission mission = daoM.dbSearchById(missionId);
            daoM.close();

            agentMission.setId(c.getLong(c.getColumnIndex("id")));
            agentMission.setAgent(agent);
            agentMission.setMission(mission);

            agentMissionList.add(agentMission);
        }
        c.close();

        return agentMissionList;
    }

    public List<Mission> dbSearchMissionsFromAgentById (Context context, Long id) {
        List<AgentMission> agentMissionList = dbSearch(context);
        List<Long> missionIdList = new ArrayList<>();
        for (AgentMission am : agentMissionList) {
            if (am.getAgent().getId() == id) {
                missionIdList.add(am.getMission().getId());
            }
        }
        MissionDAO mDao = new MissionDAO(context);
        List<Mission> missionsFromAgentList = new ArrayList<>();
        for (Long mId : missionIdList) {
            Mission mission = mDao.dbSearchById(mId);
            if (mission != null) {
                missionsFromAgentList.add(mission);
            }
        }
        return missionsFromAgentList;
    }
}
