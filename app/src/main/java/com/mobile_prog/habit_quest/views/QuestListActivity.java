package com.mobile_prog.habit_quest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

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

        Bundle extras = getIntent().getExtras();
        String questTypeId = extras.getString("quest_type_id");

        recyclerView = findViewById(R.id.recyclerview_quest);
        userQuests = new Vector<UserQuest>();
        adapter = new QuestAdapter(this, userQuests);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}