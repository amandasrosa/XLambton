package com.aa.xlambton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MissionUpdateActivity extends AppCompatActivity {
    List<String> paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_update);

        paths = new ArrayList<>();
        MissionUpdateAdapter adapter = new MissionUpdateAdapter(this, R.layout.activity_mission_update, paths);
        GridView photoGrid = (GridView) findViewById(R.id.mission_update_grid);
        photoGrid.setAdapter(adapter);
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
