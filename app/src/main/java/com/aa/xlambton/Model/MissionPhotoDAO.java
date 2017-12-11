package com.aa.xlambton.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Amanda on 11/12/2017.
 */

public class MissionPhotoDAO extends SQLiteOpenHelper {

    public MissionPhotoDAO(Context context) {
        super(context, "c0719157c0712150DB4", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE MissionPhoto (id INTEGER PRIMARY KEY, " +
                "photoPath TEXT, mission INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS MissionPhoto";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(MissionPhoto missionPhoto) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues missionPhotoData = getContentValues(missionPhoto);
        db.insert("MissionPhoto", null, missionPhotoData);
    }

    @NonNull
    private ContentValues getContentValues(MissionPhoto missionPhoto) {
        ContentValues missionPhotoData = new ContentValues();

        missionPhotoData.put("photoPath",missionPhoto.getPhotoPath());

        return missionPhotoData;
    }

    public ArrayList<MissionPhoto> dbSearch() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM MissionPhoto;";

        Cursor c = db.rawQuery(sql, null);
        ArrayList<MissionPhoto> missionPhotoList = new ArrayList<MissionPhoto>();

        while (c.moveToNext()) {
            //if (c.getString(c.getColumnIndex("photographerName")).equals(photographerName)) {
                MissionPhoto missionPhoto = new MissionPhoto();

                missionPhoto.setId(c.getInt(c.getColumnIndex("id")));
                missionPhoto.setPhotoPath(c.getString(c.getColumnIndex("photoPath")));
                //mission

                missionPhotoList.add(missionPhoto);
            //}
        }
        c.close();

        return missionPhotoList;
    }
}
