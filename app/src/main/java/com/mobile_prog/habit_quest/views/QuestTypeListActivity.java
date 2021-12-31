package com.mobile_prog.habit_quest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobile_prog.habit_quest.R;

import java.util.Vector;

import adapters.QuestTypeAdapter;
import models.QuestType;

public class QuestTypeListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<QuestType> questTypes;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_type_list);

        questTypes = new Vector<>();
        questTypes.add(new QuestType(1, "Socialize Quest", "Quest ini merupakan quest untuk bersosialisasi dengan banyak orang yang ada diluar sana", 30));
        questTypes.add(new QuestType(2, "Money Quest", "Quest ini merupakan quest untuk mendapatkan uang yang banyak", 42));
        questTypes.add(new QuestType(3, "Health Quest", "Quest ini merupakan quest untuk menjaga kesehatan tubuh agar tetap sehat", 7));

        recyclerView = findViewById(R.id.recyclerview_quest_type);

        adapter = new QuestTypeAdapter(this, questTypes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}