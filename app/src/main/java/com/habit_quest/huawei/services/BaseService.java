package com.habit_quest.huawei.services;

import com.google.firebase.firestore.FirebaseFirestore;

public abstract class BaseService {
    protected FirebaseFirestore db;
    protected String COLLECTION_NAME;
    protected String TAG;

    protected BaseService(String collectionName, String tag) {
        db = FirebaseFirestore.getInstance();
        COLLECTION_NAME = collectionName;
        TAG = tag;
    }
}
