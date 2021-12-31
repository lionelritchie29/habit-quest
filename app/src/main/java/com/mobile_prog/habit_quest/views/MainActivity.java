package com.mobile_prog.habit_quest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.mobile_prog.habit_quest.R;
import com.mobile_prog.habit_quest.services.QuestsService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, QuestTypeListActivity.class);
        startActivity(intent);
//        disableNightMode();
//        QuestsService.getInstance().getQuests();
    }

    private void disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}