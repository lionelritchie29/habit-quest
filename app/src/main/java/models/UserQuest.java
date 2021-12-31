package models;

public class UserQuest {

    public int id;
    public int userId;
    public int questTypeId;
    public int[] questDoneIds;

    public UserQuest(int id, int userId, int questTypeId, int[] questDoneIds) {
        this.id = id;
        this.userId = userId;
        this.questTypeId = questTypeId;
        this.questDoneIds = questDoneIds;
    }
}
