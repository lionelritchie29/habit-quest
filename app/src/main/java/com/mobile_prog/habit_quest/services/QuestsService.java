package com.mobile_prog.habit_quest.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.mobile_prog.habit_quest.models.Quest;
import com.mobile_prog.habit_quest.models.QuestType;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class QuestsService extends BaseService{
    private static QuestsService instance = null;

    private QuestsService() {
        super("quests", "QuestsService");
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

    public void addDummies() {
        WriteBatch batch = db.batch();

        //Socialize
        Vector<Quest> s = new Vector<>();
        s.add(new Quest("-1", null, "Say hello to 3 friends", "Say hello to your friends via social media or direct call", "Call them by name first"));
        s.add(new Quest("-1", null, "Talk with your family", "Start a conversation with one of your family", "Call them by name first"));
        s.add(new Quest("-1", null, "Ask 1 friend to hangout", "Ask your friend to hangout to the nearest cafe", "You could ask them by chat!"));
        s.add(new Quest("-1", null, "Help 1 people in need", "Go outdoor and find someone who needs your help", "Do it with pure heart!"));

        //Wealth
        Vector<Quest> w = new Vector<>();
        w.add(new Quest("-1", null, "Save Rp. 500000 a month", "Save Rp. 500.000 to your deposits in a month", "Save it in a place where you cant use it"));
        w.add(new Quest("-1", null, "Donate your money", "Donate your money to a people/organization who needs it", "You could use an application like KitaBisa"));
        w.add(new Quest("-1", null, "Spend less than Rp 200.000 a week", "Spend your money less than 200.000 a week", "Use a money tracker app to make it easier to track your outcome"));

        //Health
        Vector<Quest> h = new Vector<>();
        h.add(new Quest("-1", null, "Do 10 push ups a day", "Strengthen your muscle by doing 10 push ups a day", "Do it in the morning when you still have many energies"));
        h.add(new Quest("-1", null, "Do 10 Sit ups a day", "Strengthen your muscle by doing 10 sit ups a day", "Do it in the morning when you still have many energies"));
        h.add(new Quest("-1", null, "Do cardio for 30 minutes twice a week", "Do cardio exercise for 30 minutes twice in a week", "Follow youtube's cardiotutorial that match the duration"));
        h.add(new Quest("-1", null, "Do 10.000 walk steps twice a week", "Walk 10.000 steps twice a week", "Use health application  or smartwatch to help you with this"));

        QuestTypesService.getInstance().getAll(questTypes -> {
            for (QuestType qt: questTypes) {
                if (qt.getName().contains("Socialize")) {
                    for (Quest q: s) {
                        Map<String, Object> socializeQuest = new HashMap<>();
                        socializeQuest.put("type_id", qt.getId());
                        socializeQuest.put("name", q.getName());
                        socializeQuest.put("description", qt.getDescription());
                        socializeQuest.put("tips", q.getTips());
                        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
                        batch.set(docRef, socializeQuest);
                    }
                } else if (qt.getName().contains("Wealth")) {
                    for (Quest q: w) {
                        Map<String, Object> socializeQuest = new HashMap<>();
                        socializeQuest.put("type_id", qt.getId());
                        socializeQuest.put("name", q.getName());
                        socializeQuest.put("description", qt.getDescription());
                        socializeQuest.put("tips", q.getTips());
                        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
                        batch.set(docRef, socializeQuest);
                    }
                } else if (qt.getName().contains("Health")) {
                    for (Quest q: h) {
                        Map<String, Object> socializeQuest = new HashMap<>();
                        socializeQuest.put("type_id", qt.getId());
                        socializeQuest.put("name", q.getName());
                        socializeQuest.put("description", qt.getDescription());
                        socializeQuest.put("tips", q.getTips());
                        DocumentReference docRef = db.collection(COLLECTION_NAME).document();
                        batch.set(docRef, socializeQuest);
                    }
                }
            }

            batch.commit();
        });
    }
}
