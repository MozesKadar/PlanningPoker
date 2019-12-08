package com.example.planningpoker.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planningpoker.Objects.FirebaseHelpAdmin;
import com.example.planningpoker.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class SignInAsAdmin extends AppCompatActivity {

    private EditText adminName, adminPass,text;
    private Button signIn;
    private FirebaseHelpAdmin fb;
    private String sAdminName, sAdminPass, fbAdminName = " ", fbAdminPass = " ";
    public static final String EXTRA_ADMIN_NAME = "com.example.planningpoker.Admin";
    private Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_as_admin);
        inicialize();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sAdminName = adminName.getText().toString();
                sAdminPass = adminPass.getText().toString();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Admin");
                query = myRef.child(sAdminName);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String key = dataSnapshot.getKey();
                        if(key.equals("adminPassword")){

                            if (dataSnapshot.getValue().toString().equals(sAdminPass))
                            {
                                Intent intent = new Intent(SignInAsAdmin.this,AdminActivity.class);
                                intent.putExtra(EXTRA_ADMIN_NAME,sAdminName);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(SignInAsAdmin.this, "Rossz jelszo", Toast.LENGTH_SHORT).show();
                                adminName.setText("");
                                adminPass.setText("");
                            }

                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String key = dataSnapshot.getKey();
                        if(key.equals("adminPassword")){

                            if (dataSnapshot.getValue().toString().equals(adminPass))
                            {
                                Intent intent = new Intent(SignInAsAdmin.this,AdminActivity.class);
                                intent.putExtra(EXTRA_ADMIN_NAME,sAdminName);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(SignInAsAdmin.this, "NincsIlyen", Toast.LENGTH_SHORT).show();
                                adminName.setText("");
                                adminPass.setText("");
                            }

                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }
    public void inicialize()
    {
        adminName = findViewById(R.id.adminNameSignIn);
        adminPass = findViewById(R.id.adminPassSignIn);
        signIn = findViewById(R.id.adminSignIn);
    }
}
