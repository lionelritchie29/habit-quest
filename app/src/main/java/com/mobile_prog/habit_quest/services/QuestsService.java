package com.mobile_prog.habit_quest.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class QuestsService {
    private FirebaseFirestore db;
    private String COLLECTION_NAME = "quests";
    private static QuestsService instance = null;

    private QuestsService() {
        db = FirebaseFirestore.getInstance();
    }

    public static QuestsService getInstance() {
        if (instance == null) {
            instance = new QuestsService();
        }

        return instance;
    }

    public void getQuests() {
        db.collection(COLLECTION_NAME).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(this.getClass().getName(), document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(this.getClass().getName(), "Error getting documents.", task.getException());
                }
            }
        });
    }
}
