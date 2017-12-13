package com.aa.xlambton;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.aa.xlambton.Model.Agent;
import com.aa.xlambton.Model.AgentDAO;

import java.io.File;

public class NewAgentActivity extends AppCompatActivity {
    private String dirAppPhoto;
    ImageView imageView;
    private static final int CAMERA_CODE = 900;
    private static final int GALLERY_CODE = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_agent);

        dirAppPhoto = "";

        imageView = findViewById(R.id.new_agent_photo);
        registerForContextMenu(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContextMenu(view);
            }
        });

        Button btnSave = (Button)findViewById(R.id.new_agent_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Agent agent = CreateObjHelper.createNewAgent(NewAgentActivity.this, dirAppPhoto);
                if (agent.getName().isEmpty() || agent.getPhotoPath().isEmpty()) {
                    Toast.makeText(NewAgentActivity.this, "Insert a valid name and photo", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        AgentDAO dao = new AgentDAO(NewAgentActivity.this);
                        dao.dbInsert(agent);
                        Toast.makeText(NewAgentActivity.this, "Agent saved", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(NewAgentActivity.this, "Error while saving agent", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, final ContextMenu.ContextMenuInfo menuInfo) {
        /*MenuItem gallery = menu.add("Choose from gallery");
        gallery.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(pickPhoto , GALLERY_CODE);
                return false;
            }
        });*/

        MenuItem camera = menu.add("Take a new photo");
        camera.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dirAppPhoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File filePhoto = new File(dirAppPhoto);

                if (filePhoto != null) {
                    Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intentCamera.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri uri = FileProvider.getUriForFile(NewAgentActivity.this, BuildConfig.APPLICATION_ID, filePhoto);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                    if (intentCamera.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intentCamera, CAMERA_CODE);
                    }
                }
                return false;
            }
        });

        super.onCreateContextMenu(menu, view, menuInfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_CODE) {
                dirAppPhoto = getRealPathFromURI(data.getData());
                imageView.setImageBitmap(BitmapHelper.getScaledBitmap(NewAgentActivity.this,
                        dirAppPhoto, imageView));
            } else if (requestCode == CAMERA_CODE) {
                imageView.setImageBitmap(BitmapHelper.getScaledBitmap(NewAgentActivity.this,
                        dirAppPhoto, imageView));
            }
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(NewAgentActivity.this, contentUri,
                proj, // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
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
