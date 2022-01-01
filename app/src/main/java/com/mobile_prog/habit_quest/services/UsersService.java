package com.mobile_prog.habit_quest.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.huawei.hms.support.account.result.AuthAccount;
import com.mobile_prog.habit_quest.models.User;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class UsersService extends BaseService{

    private static UsersService instance = null;

    private UsersService() {
        super("users", "UsersService");
    }

    public static UsersService getInstance() {
        if (instance == null) {
            instance = new UsersService();
        }

        return  instance;
    }

    public void addUserIfNotExist(AuthAccount account) {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(account.getUnionId());
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                if (!doc.exists()) {
                    addUser(account);
                }
            } else {
                Log.d(TAG, "Document does not exists");
            }
        });
    }

    private void addUser(AuthAccount account) {
        User user = new User(account.getUnionId(), account.getDisplayName(), account.getEmail(), 0, 0, account.getAvatarUriString());
        db.collection(COLLECTION_NAME).document(user.getId()).set(user).addOnFailureListener(e -> Log.d(TAG, "Failed when adding user, exception: " + e.getMessage()));
    }
}
