package com.mobile_prog.habit_quest.services;

import android.util.Log;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mobile_prog.habit_quest.interfaces.Callable;
import com.mobile_prog.habit_quest.models.UserQuestType;

import java.util.Vector;

public class UserQuestTypesService extends BaseService{
    private static UserQuestTypesService instance = null;

    private UserQuestTypesService() {
        super("user_quest_types", "UserQuestTypesService");
    }

    public static UserQuestTypesService getInstance() {
        if (instance == null) {
            instance = new UserQuestTypesService();
        }

        return instance;
    }

    public void getByUser(String userId, Callable<Vector<UserQuestType>> callback) {
        db.collection(COLLECTION_NAME).whereEqualTo("user_id", userId).get().addOnCompleteListener(task -> {
            Vector<UserQuestType> userQuestTypes = new Vector<>();
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    userQuestTypes.add(new UserQuestType(
                            document.getId(),
                            document.getString("quest_type_id"),
                            document.getString("user_id"),
                            document.getBoolean("is_done")
                    ));
                }
                callback.call(userQuestTypes);
            } else {
                Log.d(TAG, "Failed when getting quest type documents by user");
                callback.call(userQuestTypes);
            }
        });
    }

    public void getAll(Callable<Vector<UserQuestType>> callback) {
        db.collection(COLLECTION_NAME).get().addOnCompleteListener(task -> {
            Vector<UserQuestType> userQuestTypes = new Vector<>();
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    userQuestTypes.add(new UserQuestType(
                            document.getId(),
                            document.getString("quest_type_id"),
                            document.getString("user_id"),
                            document.getBoolean("is_done")
                    ));
                }
                callback.call(userQuestTypes);
            } else {
                Log.d(TAG, "Failed when getting quest type documents by user");
                callback.call(userQuestTypes);
            }
        });
    }

    public void getByUserAndQuestType(String userId, String questTypeId, Callable<UserQuestType> callback) {
        db.collection(COLLECTION_NAME).whereEqualTo("quest_type_id", questTypeId).whereEqualTo("user_id", userId).get().addOnCompleteListener(task -> {
            Vector<UserQuestType> userQuestTypes = new Vector<>();
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    userQuestTypes.add(new UserQuestType(
                            document.getId(),
                            document.getString("quest_type_id"),
                            document.getString("user_id"),
                            document.getBoolean("is_done")
                    ));
                }
                callback.call(userQuestTypes.get(0));
            } else {
                Log.d(TAG, "Failed when getting quest type documents by user");
                callback.call(userQuestTypes.get(0));
            }
        });
    }
}
