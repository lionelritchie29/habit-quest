package com.mobile_prog.habit_quest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobile_prog.habit_quest.R;

import java.util.Vector;

import com.mobile_prog.habit_quest.adapters.QuestAdapter;
import com.mobile_prog.habit_quest.models.Quest;
import com.mobile_prog.habit_quest.models.QuestType;
import com.mobile_prog.habit_quest.models.User;
import com.mobile_prog.habit_quest.models.UserQuest;
import com.mobile_prog.habit_quest.models.UserQuestType;

public class QuestListActivity extends AppCompatActivity {

    Vector<UserQuest> userQuests;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        recyclerView = findViewById(R.id.recyclerview_quest);

        User dummyUser = new User(1, "vincent", "vincent@gmail.com","password", 2000, 2);
        QuestType dummyType = new QuestType(1, "Socialize Quest", "Socialize with your friends to gain more network", 30);
        UserQuestType dummyUserQuestType = new UserQuestType(1, dummyType, dummyUser, false);
        Quest quest1 = new Quest(1, dummyType, "Ask 1 Friend to Hangout", "Ask your friends to hangout with you, minimal 1, greater will be better","Ask with your manner");
        Quest quest2 = new Quest(2, dummyType, "Talk with your Family", "Ask your friends to hangout with you, minimal 1, greater will be better","Ask with your manner");
        Quest quest3 = new Quest(3, dummyType, "Chat 5 Friends", "Ask your friends to hangout with you, minimal 1, greater will be better","Ask with your manner");

        userQuests = new Vector<>();
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest1, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest2, true));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));
        userQuests.add(new UserQuest(1, dummyUserQuestType, quest3, false));


        adapter = new QuestAdapter(this, userQuests);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}