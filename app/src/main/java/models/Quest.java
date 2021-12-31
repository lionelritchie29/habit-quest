package models;

public class Quest {
    public int id;
    public QuestType type;
    public String name;
    public String description;
    public String tips;

    public Quest(int id, QuestType questType, String name, String description, String tips) {
        this.id = id;
        this.type = questType;
        this.name = name;
        this.description = description;
        this.tips = tips;
    }
}
