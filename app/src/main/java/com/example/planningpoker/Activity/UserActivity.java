package com.example.planningpoker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planningpoker.Objects.FirebaseHelpGroup;
import com.example.planningpoker.R;

public class UserActivity extends AppCompatActivity {

    private String name, groupid;
    private FirebaseHelpGroup fb;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        final Button customButton1 = findViewById(R.id.custom_button1);
        final TextView textView=findViewById(R.id.sendText);
        final EditText questionText = findViewById(R.id.questionText);
        Intent intent = getIntent();
        name = intent.getStringExtra(SignInAsUser.EXTRA_ADMIN_NAME);
        groupid = intent.getStringExtra(SignInAsUser.EXTRA_ADMIN_GPID);
        fb = new FirebaseHelpGroup(groupid);
        counter = 0;

        new CountDownTimer(2000, 1000) {
            public void onFinish() {
//                questionText.setText(fb.getGroup().getQuestions().get(counter).getQuestion());
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();


        customButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("1");

            }
        });

        final Button customButton2 = findViewById(R.id.custom_button2);


        customButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("2");
            }
        });


        final Button customButton3 = findViewById(R.id.custom_button3);


        customButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("3");
            }
        });

        final Button customButton4=findViewById(R.id.custom_button4);

        customButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("4");
            }
        });

        final Button customButton5=findViewById(R.id.custom_button5);

        customButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "Click", Toast.LENGTH_SHORT).show();
                textView.setText("5");
            }
        });

        final  Button sendButton=findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fb.addResult(groupid,name,fb.getGroup().getQuestions().get(counter).getQuestionID(),textView.getText().toString());
                counter++;
                Toast.makeText(UserActivity.this, "Valasz elkuldve", Toast.LENGTH_SHORT).show();
                textView.setText("");
                if (counter<fb.getGroup().getQuestions().size()){
                    questionText.setText(fb.getGroup().getQuestions().get(counter).getQuestion());
                }
                else{

                    Toast.makeText(UserActivity.this, "Nincs tobb kerdes", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final Button leaveButton = findViewById(R.id.leaveButton);

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

    }
}
