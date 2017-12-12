package com.aa.xlambton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnAgentList = (Button)findViewById(R.id.menu_button_agent_list);
        Button btnAgentSearch = (Button)findViewById(R.id.menu_button_agent_search);
        Button btnAgentAdd = (Button)findViewById(R.id.menu_button_add_agent);

        btnAgentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AgentListActivity.class);
                startActivity(intent);
            }
        });

        btnAgentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AgentSearchActivity.class);
                startActivity(intent);
            }
        });

        btnAgentAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MenuActivity.this, NewAgentActivity.class);
                //startActivity(intent);
            }
        });
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
