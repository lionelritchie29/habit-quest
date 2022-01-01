package com.mobile_prog.habit_quest.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.mobile_prog.habit_quest.interfaces.Callable;
import com.mobile_prog.habit_quest.models.QuestType;

import java.util.ArrayList;
import java.util.Vector;

public class QuestTypesService extends BaseService{
    private static QuestTypesService instance = null;

    private QuestTypesService() {
        super("quest_types", "QuestTypesService");
    }

    public static QuestTypesService getInstance() {
        if (instance == null) {
            instance = new QuestTypesService();
        }

        return instance;
    }

    public void getAll(Callable<Vector<QuestType>> callback) {
        db.collection(COLLECTION_NAME).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Vector<QuestType> qts = new Vector<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    QuestType qt = document.toObject(QuestType.class);
                    qt.setId(document.getId());
                    qts.add(qt);
                }

                callback.call(qts);
            } else {
                Log.d(TAG, "Failed when getting quest type documents");
            }
        });
    }

    public void addDummies() {
        ArrayList<QuestType> dummies = new ArrayList<>();
        dummies.add(new QuestType("-1", "Socialize Quest", "Socialize with your friends to gain more network", 30));
        dummies.add(new QuestType("-1", "Wealth Quest", "Manage your wealth by completing tasks in this quest", 30));
        dummies.add(new QuestType("-1", "Health Quest", "Improve your health for a better future with this simple tasks", 15));

        WriteBatch batch = db.batch();

        for (QuestType qt: dummies) {
            DocumentReference docRef = db.collection(COLLECTION_NAME).document();
            batch.set(docRef, qt);
        }

        batch.commit();
    }
}