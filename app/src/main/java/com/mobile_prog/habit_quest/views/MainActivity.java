package com.mobile_prog.habit_quest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile_prog.habit_quest.R;
import com.mobile_prog.habit_quest.adapters.QuestTypeAdapter;
import com.mobile_prog.habit_quest.contexts.AuthContext;
import com.mobile_prog.habit_quest.models.Quest;
import com.mobile_prog.habit_quest.models.QuestType;
import com.mobile_prog.habit_quest.services.QuestTypesService;
import com.mobile_prog.habit_quest.utils.Seeder;

import java.util.Vector;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView avatarImgView;
    private TextView userNameTv;
    private TextView userLevelTv;
    private Button addQuestBtn;
    private RecyclerView currentRv;
    private RecyclerView historyRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        disableNightMode();
        initialize();
        setUser();
        setRecyclerView();
    }

    private void initialize() {
        avatarImgView = findViewById(R.id.main_user_avatar);
        userNameTv = findViewById(R.id.main_user_name);
        userLevelTv = findViewById(R.id.main_user_level);
        addQuestBtn = findViewById(R.id.main_add_quest_btn);

        currentRv = findViewById(R.id.main_current_rv);
        historyRv = findViewById(R.id.main_history_rv);

        addQuestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuestTypeListActivity.class);
            startActivity(intent);
        });
    }

    private void disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void setUser() {
        userNameTv.setText(AuthContext.getName());
        userLevelTv.setText(AuthContext.getLevel().toString());

        if (AuthContext.getAvatarUriString().isEmpty()) {
            Glide.with(this).load("https://i.pravatar.cc/300").into(avatarImgView);
        } else {
            Glide.with(this).load(AuthContext.getAvatarUriString()).into(avatarImgView);
        }
    }

    private void setRecyclerView() {
        currentRv.setLayoutManager(new LinearLayoutManager(this));
        historyRv.setLayoutManager(new LinearLayoutManager(this));

        QuestTypesService.getInstance().getByUser(AuthContext.getId(), questTypes -> {
            QuestTypeAdapter adapter = new QuestTypeAdapter(this, questTypes);
            currentRv.setAdapter(adapter);
        });

        QuestTypesService.getInstance().getByUser(AuthContext.getId(), questTypes -> {
            QuestTypeAdapter adapter = new QuestTypeAdapter(this, questTypes);
            historyRv.setAdapter(adapter);
        });
    }
}