package com.mobile_prog.habit_quest.services;

public class UserQuestsService extends BaseService{
    private static UserQuestsService instance = null;

    private UserQuestsService() {
        super("user_quests", "UserQuestsService");
    }

    public static UserQuestsService getInstance() {
        if (instance == null) {
            instance = new UserQuestsService();
        }

        return instance;
    }
}
