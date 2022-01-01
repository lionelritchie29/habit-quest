package com.mobile_prog.habit_quest.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mobile_prog.habit_quest.R;
import com.mobile_prog.habit_quest.models.Quest;

public class QuestDetailActivity extends AppCompatActivity {

    private Quest quest;
    private TextView questNameTv;
    private TextView questDescTv;
    private TextView questTipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_detail);

        initialize();
        setQuest();
    }

    private void initialize() {
        this.questNameTv = findViewById(R.id.txt_detail_quest_name);
        this.questDescTv = findViewById(R.id.txt_detail_quest_desc);
        this.questTipsTv = findViewById(R.id.txt_quest_detail_tips);

        quest = (Quest) getIntent().getExtras().getSerializable("quest");
    }

    private void setQuest() {
        this.questNameTv.setText(quest.getName());
        this.questDescTv.setText(quest.getDescription());
        this.questTipsTv.setText(quest.getTips());
    }
}