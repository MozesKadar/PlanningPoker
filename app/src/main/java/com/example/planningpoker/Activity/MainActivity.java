package com.example.planningpoker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.planningpoker.R;

public class MainActivity extends AppCompatActivity {

    private Button signInUser, signInAdmin, regAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicialize();
        signInUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignInAsUser.class);
                startActivity(intent);
            }
        });
        signInAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignInAsAdmin.class);
                startActivity(intent);
            }
        });
        regAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegisterAsAdmin.class);
                startActivity(intent);
            }
        });

    }

    private void inicialize(){
        signInUser = findViewById(R.id.signInUser);
        signInAdmin = findViewById(R.id.signInAdmin);
        regAdmin = findViewById(R.id.RegAdmin);
    }
}
