package com.example.planningpoker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Calendar;

public class AdminActivity extends AppCompatActivity {

    private Button addGroup, openGroupButton, addQuestion,date,date2;
    private EditText nameGroup, adminName, openGroup ,  selectGroup, question;
    private TextView text;
    private TextView groups;
    private String sNameGroup,sSelectGroup, sQuestion,date1,date22;
    private Group gp;
    private int lastKey,lastKeyQ;
    private Question quest;
    private FirebaseHelpAdmin fb;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Groups");
    DatabaseReference myRef2 = database.getReference("Admin");
    private FirebaseHelpGroup fb_group;
    private String myGroups;
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent intent = getIntent();
        final String adminName = intent.getStringExtra(RegisterAsAdmin.EXTRA_ADMIN_NAME);
        inicialize(adminName);
        fb = new FirebaseHelpAdmin(adminName);
        getGroupLastKey();
        getQuestionLastKey();
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
                    myGroups = myGroups + " "+fb.getAdmin().getGroups().get(i)+ " "+fb.getAdmin().getGroupID().get(i);
                }
                    text.setText(myGroups);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AdminActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date1 = month + "/" + day + "/" + year;
                date.setText("Begin: " + date1);
            }
        };
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AdminActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date22 = month + "/" + day + "/" + year;
                date2.setText("End: " + date22);
            }
        };

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sSelectGroup = selectGroup.getText().toString();
                sQuestion = question.getText().toString();
                fb_group = new FirebaseHelpGroup(sSelectGroup);
                new CountDownTimer(2000, 1000) {
                    public void onFinish() {
                        if(fb_group.getGroup().getGroupOwner().equals(adminName)){
                            quest = new Question();
                            quest.setQuestion(sQuestion);
                            quest.setQuestionID(String.valueOf(++lastKeyQ));
                            quest.setAlpha(date1);
                            quest.setOmega(date22);
                            myRef.child(sSelectGroup).child("Questions").child(String.valueOf(lastKeyQ)).setValue(quest);
                        }
                        else{
                            Toast.makeText(AdminActivity.this, "You dont have Group whit this number", Toast.LENGTH_SHORT).show();

                        }
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();




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
        date = findViewById(R.id.date);
        date2 = findViewById(R.id.date2);
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
