package com.example.planningpoker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.planningpoker.R;

public class SignInAsUser extends AppCompatActivity {

    private EditText groupId, nickname;
    private Button signin;
    public static final String EXTRA_ADMIN_NAME = "com.example.planningpoker.Admin";
    public static final String EXTRA_ADMIN_GPID = "com.example.planningpoker.Admin";
    private String sGroupid, sNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_as_user);
        inicialize();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sGroupid = groupId.getText().toString();
                sNickname = nickname.getText().toString();
                Intent intent = new Intent(SignInAsUser.this,UserActivity.class);
                intent.putExtra(EXTRA_ADMIN_NAME,sNickname);
                intent.putExtra(EXTRA_ADMIN_GPID,sGroupid);
                startActivity(intent);

            }
        });

    }
    private void inicialize(){
        groupId = findViewById(R.id.groupid);
        nickname = findViewById(R.id.userNameSignIn);
        signin = findViewById(R.id.userSignIn);
    }
}
