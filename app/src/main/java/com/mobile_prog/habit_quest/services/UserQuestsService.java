package com.mobile_prog.habit_quest.services;

import android.util.Log;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mobile_prog.habit_quest.interfaces.Callable;
import com.mobile_prog.habit_quest.models.Quest;
import com.mobile_prog.habit_quest.models.UserQuest;

import java.util.Vector;

public class UserQuestsService extends BaseService{
    private static UserQuestsService instance = null;

    private UserQuestsService() {
        super("user_quests", "UserQuestsService");
    }

    public static UserQuestsService getInstance() {
        if (instance == null) {
            instance = new UserQuestsService();
        }

        return instance;
    }

    public void getByUserAndQuestType(String userId, String questTypeId, Callable<Vector<UserQuest>> callback) {
        UserQuestTypesService.getInstance().getByUserAndQuestType(userId, questTypeId, userQuestType -> {
            db.collection(COLLECTION_NAME).whereEqualTo("user_quest_type_id", userQuestType.getId()).get().addOnCompleteListener(task -> {
                Vector<UserQuest> userQuests = new Vector<>();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        userQuests.add(new UserQuest(
                                document.getId(),
                                document.getString("user_quest_type_id"),
                                document.getString("quest_id"),
                                document.getBoolean("is_done")
                        ));
                    }

                    callback.call(userQuests);
                } else {
                    Log.w(this.getClass().getName(), "Error getting user quests documents.", task.getException());
                    callback.call(userQuests);
                }
            });
        });
    }
}
