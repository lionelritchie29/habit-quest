package com.mobile_prog.habit_quest.services;

import android.util.Log;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mobile_prog.habit_quest.interfaces.Callable;
import com.mobile_prog.habit_quest.models.QuestType;
import com.mobile_prog.habit_quest.models.UserQuestType;

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

    public void getByUser(String userId, Callable<Vector<QuestType>> callback) {
        UserQuestTypesService.getInstance().getByUser(userId, userQuestTypes -> {
            Vector<String> questIds = new Vector<>();
            for (UserQuestType uqt: userQuestTypes) {
                questIds.add(uqt.getTypeId());
            }

            db.collection(COLLECTION_NAME).whereIn("__name__", questIds).get().addOnCompleteListener(task -> {
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
        });
    }
}