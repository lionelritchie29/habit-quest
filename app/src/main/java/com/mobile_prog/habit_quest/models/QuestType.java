package com.mobile_prog.habit_quest.models;

public class QuestType {

    public int id;
    public String name;
    public String description;
    public int day;

    public QuestType(int id, String name, String description, int day) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.day = day;
    }
}
