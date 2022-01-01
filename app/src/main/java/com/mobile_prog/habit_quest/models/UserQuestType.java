package com.mobile_prog.habit_quest.models;

public class UserQuestType {
    private String id;
    private String typeId;
    private String userId;
    private boolean isDone;

    public UserQuestType(String id, String typeId, String userId, boolean isDone) {
        this.id = id;
        this.typeId = typeId;
        this.userId = userId;
        this.isDone = isDone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
