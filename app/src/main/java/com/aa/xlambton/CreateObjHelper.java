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

/**
 * Created by Amanda on 11/12/2017.
 */

public class CreateObjHelper {

    public void createMissions(Context context) throws ParseException {

        MissionDAO dao = new MissionDAO(context);

        Mission m1 = new Mission();
        m1.setName("Europe Heat Zone");
        m1.setDate("28/03/2016");
        m1.setStatus("Done");
        dao.dbInsert(m1);

        Mission m2 = new Mission();
        m2.setName("Indian Investigation");
        m2.setDate("16/05/2016");
        m2.setStatus("Done");

        Mission m3 = new Mission();
        m3.setName("America Threat");
        m3.setDate("03/10/2017");
        m3.setStatus("On going");

        Mission m4 = new Mission();
        m4.setName("South Asia Detection");
        m4.setDate("11/12/2017");
        m4.setStatus("On going");

        Mission m5 = new Mission();
        m5.setName("Middle East Searching");
        m5.setDate("19/06/2017");
        m5.setStatus("Canceled");

    }

    public void createAgents(Context context) {

        AgentDAO dao = new AgentDAO(context);

        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent1) ;
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent2) ;
        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent3) ;
        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent4) ;
        Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.agent5) ;

        Agent ag1 = new Agent();
        ag1.setName("Thomas Roy");
        ag1.setLevel("001");
        ag1.setAgency("AIC");
        ag1.setWebsite("http://www.aic.com");
        ag1.setCountry("France");
        ag1.setPhoneNumber("+16477209208");
        ag1.setAddress("6 Rue Dulac, Paris, France");
        ag1.setPhoto(bitmap1);
        dao.dbInsert(ag1);

        Agent ag2 = new Agent();
        ag2.setName("Olivia Brown");
        ag2.setLevel("002");
        ag2.setAgency("MSD");
        ag2.setWebsite("http://www.msd.com");
        ag2.setCountry("Hungary");
        ag2.setPhoneNumber("+16490569211");
        ag2.setAddress("Szinyei Merse u. 32, Budapest, Hungary");
        ag2.setPhoto(bitmap2);
        dao.dbInsert(ag2);

        Agent ag3 = new Agent();
        ag3.setName("Samuel Bouchard");
        ag3.setLevel("003");
        ag3.setAgency("BGK");
        ag3.setWebsite("http://www.bgk.com");
        ag3.setCountry("Monaco");
        ag3.setPhoneNumber("+16490569211");
        ag3.setAddress("Szinyei Merse u. 32, Budapest, Hungary");
        ag3.setPhoto(bitmap3);
        dao.dbInsert(ag3);

        Agent ag4 = new Agent();
        ag4.setName("Rebecca Lee");
        ag4.setLevel("001");
        ag4.setAgency("AIC");
        ag4.setWebsite("http://www.aic.com");
        ag4.setCountry("Turkey");
        ag4.setPhoneNumber("+1678670237");
        ag4.setAddress("Szinyei Merse u. 32, , Turkey");
        ag4.setPhoto(bitmap4);
        dao.dbInsert(ag4);

        Agent ag5 = new Agent();
        ag5.setName("Logan Gagnon");
        ag5.setLevel("002");
        ag5.setAgency("AIC");
        ag5.setWebsite("http://www.aic.com");
        ag5.setCountry("Greece");
        ag5.setPhoneNumber("+163909730");
        ag5.setAddress("Szinyei Merse u. 32, , Greece");
        ag5.setPhoto(bitmap5);
        dao.dbInsert(ag5);
        
    }

    public void createAgentMissions(Context context) {

        AgentMissionDAO dao = new AgentMissionDAO(context);

        AgentMission agm1 = new AgentMission();
    }
}
