package models;

import java.util.Date;

public class UserQuestType {

    public int id;
    public int questTypeId;
    public int userId;
    public Date startDate;
    public boolean isDone;

    public UserQuestType(int id, int questTypeId, int userId,Date startDate, boolean isDone) {
        this.id = id;
        this.questTypeId = questTypeId;
        this.userId = userId;
        this.startDate = startDate;
        this.isDone = isDone;
    }
}