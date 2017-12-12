package com.aa.xlambton;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aa.xlambton.Model.Agent;

import java.io.File;

public class AgentProfileActivity extends AppCompatActivity {

    private static final int CAMERA_CODE = 990;
    private String dirAppPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_profile);

        final Agent agent = (Agent)getIntent().getSerializableExtra("agent");

        if (agent != null) {
            CreateObjHelper.fillForm(this, agent);
        }

        Button btnWebsite = (Button)findViewById(R.id.agent_profile_button_website);
        Button btnCall = (Button)findViewById(R.id.agent_profile_button_call);
        Button btnLocation = (Button)findViewById(R.id.agent_profile_button_location);
        Button btnInfo = (Button)findViewById(R.id.agent_profile_button_info);
        Button btnSms = (Button)findViewById(R.id.agent_profile_button_sms);
        Button btnCamera = (Button)findViewById(R.id.agent_profile_button_camera);

        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String site = agent.getWebsite();
                if (!site.startsWith("http://")) { site = "http://" + site; }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(site));
                startActivity(intent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(AgentProfileActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AgentProfileActivity.this, new String[] {Manifest.permission.CALL_PHONE},123);

                } else {
                    Intent itemCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + agent.getPhoneNumber()));
                    startActivity(itemCall);
                }
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLocation = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + agent.getAddress()));
                startActivity(intentLocation);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgentProfileActivity.this, MissionHistoricActivity.class);
                startActivity(intent);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSms = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"));
                startActivity(intentSms);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dirAppPhoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File filePhoto = new File(dirAppPhoto);

                if (filePhoto != null) {
                    Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intentCamera.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri uri = FileProvider.getUriForFile(view.getContext(), BuildConfig.APPLICATION_ID, filePhoto);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                    if (intentCamera.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intentCamera, CAMERA_CODE);
                    }
                }
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
