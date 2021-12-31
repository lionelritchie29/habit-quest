package models;

public class Quest {
    public int id;
    public int typeId;
    public String name;
    public String description;
    public String tips;

    public Quest(int id, int typeId, String name, String description, String tips) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.description = description;
        this.tips = tips;
    }
}
