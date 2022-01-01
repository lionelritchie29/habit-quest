package com.mobile_prog.habit_quest.models;

public class User {
    public Integer id;
    private String username;
    public String email;
    public String password;
    public int exp;
    public int level;

    public User(Integer id, String username, String email, String password, int exp, int level) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.exp = exp;
        this.level = level;
    }
}
