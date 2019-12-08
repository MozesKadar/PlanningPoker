package com.example.planningpoker.Objects;

import java.util.ArrayList;

public class Admin {

    private String adminName, adminPassword;
    private ArrayList<String> groups;
    private ArrayList<String> groupID;

    public Admin(String adminName, String adminPassword) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        groups = new ArrayList<>();
        groupID = new ArrayList<>();
    }

    public Admin() {
        groups = new ArrayList<>();

    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups.add(groups);
    }

    public ArrayList<String> getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID.add(groupID);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", groups=" + groups +
                '}';
    }
}
