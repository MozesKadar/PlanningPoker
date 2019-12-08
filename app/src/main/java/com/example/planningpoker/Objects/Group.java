package com.example.planningpoker.Objects;

import java.util.ArrayList;

public class Group {
    private String groupID, groupName, groupOwner;
    private ArrayList<Question> questions;

    public Group(String groupID, String groupName, String groupOwner) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.groupOwner = groupOwner;
        questions = new ArrayList<>();

    }

    public Group() {
        questions = new ArrayList<>();

    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(String groupOwner) {
        this.groupOwner = groupOwner;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions.add(questions);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID='" + groupID + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupOwner='" + groupOwner + '\'' +
                ", questions=" + questions +
                '}';
    }
}
