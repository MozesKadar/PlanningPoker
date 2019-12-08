package com.example.planningpoker.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FirebaseHelpAdmin {

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Query query;
    private Admin admin;

    public FirebaseHelpAdmin(String adminName) {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Admin");
        admin = new Admin();
        getData(adminName);
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void getData (String adminName){
        query = myRef.child(adminName);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                if (key.equals("adminName")){
                    admin.setAdminName(dataSnapshot.getValue().toString());
                }
                if (key.equals("adminPassword")){
                    admin.setAdminPassword(dataSnapshot.getValue().toString());
                }
                if (key.equals("Groups")){
                    for(DataSnapshot child: dataSnapshot.getChildren()){
                        admin.setGroups(child.getValue().toString());
                        admin.setGroupID(child.getKey());
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                if (key.equals("adminName")){
                    admin.setAdminName(dataSnapshot.getValue().toString());
                }
                if (key.equals("adminPassword")){
                    admin.setAdminPassword(dataSnapshot.getValue().toString());
                }
                if (key.equals("Groups")){
                    for(DataSnapshot child: dataSnapshot.getChildren()){
                        admin.setGroups(child.getValue().toString());

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
}
