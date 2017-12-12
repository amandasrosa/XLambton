package com.aa.xlambton;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.aa.xlambton.Model.Agent;
import com.aa.xlambton.Model.AgentDAO;
import com.aa.xlambton.Model.AgentMission;
import com.aa.xlambton.Model.AgentMissionDAO;
import com.aa.xlambton.Model.Mission;
import com.aa.xlambton.Model.MissionDAO;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Amanda on 11/12/2017.
 */

public class CreateObjHelper {

    public static void createObjs(Context context) throws ParseException {

        MissionDAO daoMission = new MissionDAO(context);

        Mission m1 = new Mission();
        m1.setName("Europe Heat Zone");
        m1.setDateMission("28/03/2016");
        m1.setStatus("Done");
        daoMission.dbInsert(m1);

        Mission m2 = new Mission();
        m2.setName("Indian Investigation");
        m2.setDateMission("16/05/2016");
        m2.setStatus("Done");
        daoMission.dbInsert(m2);

        Mission m3 = new Mission();
        m3.setName("America Threat");
        m3.setDateMission("03/10/2017");
        m3.setStatus("On going");
        daoMission.dbInsert(m3);

        Mission m4 = new Mission();
        m4.setName("South Asia Detection");
        m4.setDateMission("11/12/2017");
        m4.setStatus("On going");
        daoMission.dbInsert(m4);

        Mission m5 = new Mission();
        m5.setName("Middle East Searching");
        m5.setDateMission("19/06/2017");
        m5.setStatus("Canceled");
        daoMission.dbInsert(m5);

        daoMission.close();

        AgentDAO daoAgent = new AgentDAO(context);

        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent1) ;
        Bitmap lowdefbitmap1 = Bitmap.createScaledBitmap(bitmap1,300,300,true);
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent2) ;
        Bitmap lowdefbitmap2 = Bitmap.createScaledBitmap(bitmap2,300,300,true);
        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent3) ;
        Bitmap lowdefbitmap3 = Bitmap.createScaledBitmap(bitmap3,300,300,true);

        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent4) ;
        Bitmap lowdefbitmap4 = Bitmap.createScaledBitmap(bitmap4,300,300,true);
        Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent5) ;
        Bitmap lowdefbitmap5 = Bitmap.createScaledBitmap(bitmap5,300,300,true);

        Agent ag1 = new Agent();
        ag1.setName("Olivia Brown");
        ag1.setLevel("001");
        ag1.setAgency("AIC");
        ag1.setWebsite("http://www.aic.com");
        ag1.setCountry("France");
        ag1.setPhoneNumber("+16477209208");
        ag1.setAddress("6 Rue Dulac, Paris, France");
        ag1.setPhoto(lowdefbitmap1);
        daoAgent.dbInsert(ag1);

        Agent ag2 = new Agent();
        ag2.setName("Thomas Roy");
        ag2.setLevel("002");
        ag2.setAgency("MSD");
        ag2.setWebsite("http://www.msd.com");
        ag2.setCountry("Hungary");
        ag2.setPhoneNumber("+16490569211");
        ag2.setAddress("Szinyei Merse u. 32, Budapest, Hungary");
        ag2.setPhoto(lowdefbitmap2);
        daoAgent.dbInsert(ag2);

        Agent ag3 = new Agent();
        ag3.setName("Rebecca Lee");
        ag3.setLevel("003");
        ag3.setAgency("BGK");
        ag3.setWebsite("http://www.bgk.com");
        ag3.setCountry("Monaco");
        ag3.setPhoneNumber("+16490569211");
        ag3.setAddress("18 Rue Malbousquet, Monaco, Monaco");
        ag3.setPhoto(lowdefbitmap3);
        daoAgent.dbInsert(ag3);

        Agent ag4 = new Agent();
        ag4.setName("Samuel Bouchard");
        ag4.setLevel("001");
        ag4.setAgency("AIC");
        ag4.setWebsite("http://www.aic.com");
        ag4.setCountry("Turkey");
        ag4.setPhoneNumber("+1678670237");
        ag4.setAddress("Kabakulak Cami Sk. No:11, Istanbul, Turkey");
        ag4.setPhoto(lowdefbitmap4);
        daoAgent.dbInsert(ag4);

        Agent ag5 = new Agent();
        ag5.setName("Logan Gagnon");
        ag5.setLevel("002");
        ag5.setAgency("AIC");
        ag5.setWebsite("http://www.aic.com");
        ag5.setCountry("Greece");
        ag5.setPhoneNumber("+163909730");
        ag5.setAddress("Pindaro 6, Athens, Greece");
        ag5.setPhoto(lowdefbitmap5);
        daoAgent.dbInsert(ag5);

        daoAgent.close();

        AgentMissionDAO daoAgMi = new AgentMissionDAO(context);

        AgentMission agm1 = new AgentMission();
        agm1.setAgent(ag1);
        agm1.setMission(m1);
        daoAgMi.dbInsert(agm1);

        AgentMission agm2 = new AgentMission();
        agm2.setAgent(ag2);
        agm2.setMission(m2);
        daoAgMi.dbInsert(agm2);

        AgentMission agm3 = new AgentMission();
        agm3.setAgent(ag3);
        agm3.setMission(m1);
        daoAgMi.dbInsert(agm3);

        AgentMission agm4 = new AgentMission();
        agm4.setAgent(ag4);
        agm4.setMission(m2);
        daoAgMi.dbInsert(agm4);

        AgentMission agm5 = new AgentMission();
        agm5.setAgent(ag5);
        agm5.setMission(m1);
        daoAgMi.dbInsert(agm5);

        AgentMission agm6 = new AgentMission();
        agm6.setAgent(ag1);
        agm6.setMission(m3);
        daoAgMi.dbInsert(agm6);

        AgentMission agm7 = new AgentMission();
        agm7.setAgent(ag2);
        agm7.setMission(m4);
        daoAgMi.dbInsert(agm7);

        AgentMission agm8 = new AgentMission();
        agm8.setAgent(ag3);
        agm8.setMission(m3);
        daoAgMi.dbInsert(agm8);

        AgentMission agm9 = new AgentMission();
        agm9.setAgent(ag4);
        agm9.setMission(m4);
        daoAgMi.dbInsert(agm9);

        AgentMission agm10 = new AgentMission();
        agm10.setAgent(ag5);
        agm10.setMission(m3);
        daoAgMi.dbInsert(agm10);

        AgentMission agm11 = new AgentMission();
        agm11.setAgent(ag2);
        agm11.setMission(m5);
        daoAgMi.dbInsert(agm11);

        AgentMission agm12 = new AgentMission();
        agm12.setAgent(ag3);
        agm12.setMission(m5);
        daoAgMi.dbInsert(agm12);

        daoAgMi.close();
    }
}
