package com.aa.xlambton;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aa.xlambton.Model.Agent;
import com.aa.xlambton.Model.AgentDAO;

import java.util.ArrayList;
import java.util.List;

public class AgentSearchActivity extends AppCompatActivity {
    EditText agentName;
    ListView agentList;
    List<Agent> agents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_search);

        agentName = findViewById(R.id.agent_search_name);
        agentName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchAgent();
                    handled = true;
                }
                return handled;
            }
        });

        agents = new ArrayList<>();
        agentList = findViewById(R.id.agent_search_list);
        final AgentListAdapter adapter = new AgentListAdapter(this, R.layout.activity_agent_search, agents);
        agentList.setAdapter(adapter);
        agentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agent agent = (Agent) agentList.getItemAtPosition(position);
                Intent intent = new Intent(AgentSearchActivity.this, AgentProfileActivity.class);
                intent.putExtra("agent", agent);
                startActivity(intent);
            }
        });

        Button searchButton = (Button) findViewById(R.id.agent_search_button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAgent();
            }
        });
    }

    public void searchAgent() {
        String name = agentName.getText().toString();
        AgentDAO dao = new AgentDAO(AgentSearchActivity.this);
        agents = dao.dbSearchByName(name);
        AgentListAdapter adapter = (AgentListAdapter) agentList.getAdapter();
        adapter.clear();
        if (agents.isEmpty() || agents == null) {
            Toast.makeText(AgentSearchActivity.this,"Search returned no results.", Toast.LENGTH_LONG).show();
        } else {
            adapter.addAll(agents);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(agentName.getWindowToken(), 0);
        }
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
