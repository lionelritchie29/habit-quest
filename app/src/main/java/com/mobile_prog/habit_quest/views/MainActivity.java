package com.mobile_prog.habit_quest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile_prog.habit_quest.R;
import com.mobile_prog.habit_quest.contexts.AuthContext;
import com.mobile_prog.habit_quest.utils.Seeder;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView avatarImgView;
    private TextView userNameTv;
    private TextView userLevelTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, QuestTypeListActivity.class);
        startActivity(intent);
        disableNightMode();
        initialize();
        setUser();
    }

    private void initialize() {
        avatarImgView = findViewById(R.id.main_user_avatar);
        userNameTv = findViewById(R.id.main_user_name);
        userLevelTv = findViewById(R.id.main_user_level);
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
}