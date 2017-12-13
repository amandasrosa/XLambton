package com.aa.xlambton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aa.xlambton.Model.User;
import com.aa.xlambton.Model.UserDAO;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Button newUserRegister = (Button)findViewById(R.id.new_user_register);

        newUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.new_user_username)).getText().toString();
                String password = ((EditText) findViewById(R.id.new_user_password)).getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(NewUserActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(MachineCypher.encryptMessage(password));
                    UserDAO dao = new UserDAO(NewUserActivity.this);

                    if (dao.checkIfUserExists(username)) {
                        Toast.makeText(NewUserActivity.this, "User " + user.getUsername() + " already exists",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        dao.dbInsert(user);
                        dao.close();
                        Toast.makeText(NewUserActivity.this, "User " + user.getUsername() + " saved",
                                Toast.LENGTH_SHORT).show();
                        finish();
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
