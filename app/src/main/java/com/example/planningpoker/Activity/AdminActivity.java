package com.example.planningpoker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.planningpoker.Objects.FirebaseHelpAdmin;
import com.example.planningpoker.Objects.FirebaseHelpGroup;
import com.example.planningpoker.Objects.Group;
import com.example.planningpoker.Objects.Question;
import com.example.planningpoker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    private Button addGroup, openGroupButton, addQuestion;
    private EditText nameGroup, adminName, openGroup , text, selectGroup, question;
    private TextView groups;
    private String sNameGroup,sSelectGroup, sQuestion;
    private Group gp;
    private int lastKey,lastKeyQ;
    private Question quest;
    private FirebaseHelpAdmin fb;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Groups");
    DatabaseReference myRef2 = database.getReference("Admin");
    private FirebaseHelpGroup fb_group;
    private String myGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent intent = getIntent();
        final String adminName = intent.getStringExtra(RegisterAsAdmin.EXTRA_ADMIN_NAME);
        inicialize(adminName);
        fb = new FirebaseHelpAdmin(adminName);
        new CountDownTimer(2000, 1000) {
            public void onFinish() {

            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();


        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGroupLastKey();
                gp = new Group();
                sNameGroup = nameGroup.getText().toString();
                gp.setGroupName(sNameGroup);
                gp.setGroupOwner(adminName);
                gp.setGroupID(String.valueOf(++lastKey));
                myRef.child(String.valueOf(lastKey)).setValue(gp);
                myRef2.child(adminName).child("Groups").child(String.valueOf(lastKey)).setValue(sNameGroup);
            }
        });
        openGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<fb.getAdmin().getGroups().size();i++){
                    myGroups = myGroups + " "+fb.getAdmin().getGroups().get(i);
                }
                    text.setText(myGroups);
            }
        });
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuestionLastKey();
                sSelectGroup = selectGroup.getText().toString();
                sQuestion = question.getText().toString();
                quest = new Question();
                quest.setQuestion(sQuestion);
                quest.setQuestionID(String.valueOf(++lastKeyQ));
                myRef.child(sSelectGroup).child("Questions").child(String.valueOf(lastKeyQ)).setValue(quest);


            }
        });
    }

    private void inicialize (String x){
        addGroup = findViewById(R.id.addGroup);
        nameGroup = findViewById(R.id.nameGroup);
        adminName = findViewById(R.id.adminName);
        adminName.setText(x);
        openGroupButton = findViewById(R.id.openGroupButton);
        openGroup = findViewById(R.id.openGroup);
        text = findViewById(R.id.text);
        question = findViewById(R.id.question);
        selectGroup = findViewById(R.id.selectGroup);
        addQuestion = findViewById(R.id.addQuestion);
    }
    private void getGroupLastKey()
    {
        Query query = myRef.orderByChild("GroupID").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key;
                    key = child.getKey();
                    try {
                        setLastKey(Integer.parseInt(key));
                    }catch (NumberFormatException e)
                    {
                        Log.i("FBDBERROR",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("FBDBERROR",databaseError.toString());
            }
        });
    }

    public int getLastKey() {
        return lastKey;
    }

    public void setLastKey(int lastKey) {
        Log.i("FBDB","session_last_ID: "+lastKey);
        this.lastKey = lastKey;
    }
    private void getQuestionLastKey()
    {
        Query query = myRef.child("Questions").orderByChild("questionID").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key;
                    key = child.getKey();
                    Log.i("FBDB","session_last_ID: "+key);
                    try {
                        setLastKey(Integer.parseInt(key));
                    }catch (NumberFormatException e)
                    {
                        Log.i("FBDBERROR",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("FBDBERROR",databaseError.toString());
            }
        });
    }

    public int getLastKeyQ() {
        return lastKeyQ;
    }

    public void setLastKeyQ(int lastKeyQ) {
        Log.i("FBDB","session_last_ID: "+lastKeyQ);
        this.lastKeyQ = lastKeyQ;
    }
}
