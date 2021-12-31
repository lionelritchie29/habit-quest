package models;

public class UserQuest {

    public int id;
    public UserQuestType userQuestType;
    public Quest quest;
    public boolean isDone;

    public UserQuest(int id, UserQuestType userQuestType, Quest quest, boolean isDone) {
        this.id = id;
        this.userQuestType = userQuestType;
        this.quest = quest;
        this.isDone = isDone;
    }
}
