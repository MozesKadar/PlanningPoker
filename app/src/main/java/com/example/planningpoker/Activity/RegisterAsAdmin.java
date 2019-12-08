package com.example.planningpoker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planningpoker.Objects.Admin;
import com.example.planningpoker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterAsAdmin extends AppCompatActivity {

    private EditText name,pass;
    private Button reg;
    private String sName, sPass;
    private Admin admin;
    public static final String EXTRA_ADMIN_NAME = "com.example.planningpoker.Admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as_admin);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Admin");
        inicialize();
        admin = new Admin();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName = name.getText().toString();
                sPass = pass.getText().toString();
                admin.setAdminName(sName);
                admin.setAdminPassword(sPass);
                myRef.child(sName).setValue(admin);
                Intent intent = new Intent(RegisterAsAdmin.this,AdminActivity.class);
                 intent.putExtra(EXTRA_ADMIN_NAME,sName);
                startActivity(intent);


            }
        });
    }

    private void inicialize(){
        name = findViewById(R.id.adminNameSignIn);
        pass = findViewById(R.id.adminPassSignIn);
        reg = findViewById(R.id.adminSignIn);
    }

}
