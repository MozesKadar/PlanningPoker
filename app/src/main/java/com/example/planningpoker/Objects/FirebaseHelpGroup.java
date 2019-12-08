package com.example.planningpoker.Objects;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FirebaseHelpGroup {
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Query query;
    private Group group;
    private Question kerdes;
    private Result result;
    private Result d;

    public FirebaseHelpGroup(String id) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Groups");
        group = new Group();
        getData(id);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public void getData(String GroupId){
        query = myRef.child(GroupId);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                if(key.equals("groupID")){
                    group.setGroupID(dataSnapshot.getValue().toString());
                }
                if (key.equals("groupName")){
                    group.setGroupName(dataSnapshot.getValue().toString());
                }
                if (key.equals("groupOwner")){
                    group.setGroupOwner(dataSnapshot.getValue().toString());
                }
                if (key.equals("Questions")){
                    group.getQuestions().clear();
                   for (DataSnapshot child: dataSnapshot.getChildren()){
                        kerdes = new Question();
                        kerdes.setQuestion(child.child("question").getValue().toString());
                        kerdes.setQuestionID(child.child("questionID").getValue().toString());
                        group.setQuestions(kerdes);
                        int x =-1;
                        for (int i=0;i<group.getQuestions().size();i++)
                        {
                            if (group.getQuestions().get(i).getQuestionID().equals(kerdes.getQuestionID()))
                            {
                                x = i;
                                break;

                            }
                        }
                        for (DataSnapshot child1: child.getChildren())
                        {
                         /*   if (child1.getKey().equals("Result")) {
                                result = new Result();
                                result.setResultOwner(child1.child().getKey());
                                result.setResult(child1.getValue().toString());
                                group.getQuestions().get(x).setResults(result);
                            }*/
                        }

                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

    public void addQuestion(String GroupID , Question question){
        myRef.child(GroupID).child("Questions").child(question.getQuestionID()).setValue(question);
    }

 public void addResult(String groupID, String userName, String questionID, String result){
        myRef.child(groupID).child("Questions").child(questionID).child("Result").child(userName).setValue(result);
 }
}
