package com.aa.xlambton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aa.xlambton.Model.UserDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginSubmit = (Button)findViewById(R.id.login_submit);
        Button loginRegister = (Button)findViewById(R.id.login_register);

        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.login_username)).getText().toString();
                String password = ((EditText) findViewById(R.id.login_password)).getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                } else {
                    UserDAO dao = new UserDAO(LoginActivity.this);

                    if (dao.checkUserNPass(username, MachineCypher.encryptMessage(password))) {
                        Toast.makeText(LoginActivity.this, "Welcome " + username, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
                startActivity(intent);
            }
        });

    }
}
