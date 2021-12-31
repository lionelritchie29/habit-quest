package com.mobile_prog.habit_quest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Vector;

import adapters.QuestAdapter;
import adapters.QuestTypeAdapter;
import models.Quest;

public class QuestListActivity extends AppCompatActivity {

    Vector<Quest> quests;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        quests = new Vector<>();
        quests.add(new Quest(1, 1, "Ask 1 Friend to Hangout", "Ask your friends to hangout with you, minimal 1, greater will be better","Ask with your manner"));
        quests.add(new Quest(2, 1, "Chat 5 Friends", "Ask your friends to hangout with you, minimal 1, greater will be better","Ask with your manner"));
        quests.add(new Quest(3, 1, "Talk with your Family", "Ask your friends to hangout with you, minimal 1, greater will be better","Ask with your manner"));
        quests.add(new Quest(4, 1, "Post 1 Feed on instagram", "Ask your friends to hangout with you, minimal 1, greater will be better","Ask with your manner"));

        recyclerView = findViewById(R.id.recyclerview_quest);

        adapter = new QuestAdapter(this, quests);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}