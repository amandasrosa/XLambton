package com.aa.xlambton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aa.xlambton.Model.Agent;
import com.aa.xlambton.Model.AgentDAO;

import java.util.List;

public class AgentListActivity extends AppCompatActivity {

    private ListView agentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_list);

        agentsList = (ListView)findViewById(R.id.agent_list);
        registerForContextMenu(agentsList);

        agentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agent agent = (Agent) agentsList.getItemAtPosition(position);

                Intent intent = new Intent(AgentListActivity.this, AgentProfileActivity.class);
                intent.putExtra("agent", agent);
                startActivity(intent);
            }
        });
    }

    private void loadAgentsList() {

        AgentDAO dao = new AgentDAO(this);
        List<Agent> agents = dao.dbSearch();
        dao.close();

        ArrayAdapter<Agent> adapter = new ArrayAdapter<Agent>(this, android.R.layout.simple_list_item_1, agents);
        agentsList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        loadAgentsList();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_back, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Agent agent = (Agent) agentsList.getItemAtPosition(info.position);

        MenuItem delete = menu.add("Delete");

        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AgentDAO dao = new AgentDAO(AgentListActivity.this);
                dao.dbDelete(agent);
                dao.close();

                Toast.makeText(AgentListActivity.this,"Delete agent " + agent.getName(), Toast.LENGTH_SHORT).show();

                loadAgentsList();
                return false;
            }
        });

        super.onCreateContextMenu(menu,v,menuInfo);
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
