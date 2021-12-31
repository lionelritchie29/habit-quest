package models;

public class UserQuestType {
    public int id;
    public QuestType type;
    public User user;
    public boolean isDone;

    public UserQuestType(int id, QuestType type, User user, boolean isDone) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.isDone = isDone;
    }
}
