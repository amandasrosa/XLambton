package com.aa.xlambton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.aa.xlambton.Model.Mission;
import com.aa.xlambton.Model.MissionDAO;

import java.util.List;

public class MissionHistoricActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_historic);

        MissionDAO dao = new MissionDAO(this);
        List<Mission> missions = dao.dbSearch();
        dao.close();

        HistoricListAdapter adapter = new HistoricListAdapter(this, R.layout.activity_mission_historic, missions);
        ListView missionList = (ListView) findViewById(R.id.historic_mission_list);
        missionList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_back, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_back:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
