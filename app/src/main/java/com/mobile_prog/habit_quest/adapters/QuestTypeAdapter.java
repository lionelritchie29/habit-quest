package com.mobile_prog.habit_quest.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile_prog.habit_quest.contexts.AuthContext;
import com.mobile_prog.habit_quest.services.QuestTypesService;
import com.mobile_prog.habit_quest.services.UserQuestTypesService;
import com.mobile_prog.habit_quest.services.UserQuestsService;
import com.mobile_prog.habit_quest.views.MainActivity;
import com.mobile_prog.habit_quest.views.QuestListActivity;
import com.mobile_prog.habit_quest.R;

import java.util.Vector;

import com.mobile_prog.habit_quest.models.QuestType;

public class QuestTypeAdapter extends RecyclerView.Adapter<QuestTypeAdapter.QuestTypeViewHolder> {

    LayoutInflater mInflater;
    Vector<QuestType> questTypes;
    Context context;
    private boolean isForEnroll = false;

    public QuestTypeAdapter(Context context, Vector<QuestType> questTypes){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.questTypes = questTypes;
    }

    public QuestTypeAdapter(Context context, Vector<QuestType> questTypes, boolean isForEnroll){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.questTypes = questTypes;
        this.isForEnroll = true;
    }

    @NonNull
    @Override
    public QuestTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.quest_type_item, parent, false);
        return new QuestTypeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestTypeViewHolder holder, int position) {
        QuestType currentQuestType = questTypes.get(position);
        holder.questTypeName.setText(currentQuestType.getName());
        holder.questTypeDescription.setText(currentQuestType.getDescription());
        holder.questTypeDay.setText(currentQuestType.getDay() + " days");
        holder.btnDoQuest.setTag(position);

        holder.btnDoQuest.setOnClickListener(v -> {
            if (isForEnroll) {
                // add quest type and its related quests to the db for the corresponding user
                String questTypeId = questTypes.get(position).getId();
                UserQuestTypesService.getInstance().add(AuthContext.getId(), questTypeId, userQuestTypeId -> {
                    UserQuestsService.getInstance().addForUserQuestType(userQuestTypeId, questTypeId, __ -> {
                        Toast.makeText(v.getContext(), "Sucesfully added this quest to your quest list!", Toast.LENGTH_SHORT).show();
                        Intent toHome = new Intent(v.getContext(), MainActivity.class);
                        context.startActivity(toHome);
                    });
                });
            } else {
                // go to quest list
                Intent toQuestList = new Intent(context, QuestListActivity.class);
                toQuestList.putExtra("quest_type_id", questTypes.get(position).getId());
                context.startActivity(toQuestList);
            }
        });

        if (isForEnroll) {
            holder.btnDoQuest.setText("Take Quest");
        } else {
            holder.btnDoQuest.setText("Do Quest");
        }
    }

    @Override
    public int getItemCount() {
        return questTypes.size();
    }

    class QuestTypeViewHolder extends RecyclerView.ViewHolder {

        TextView questTypeName, questTypeDescription, questTypeDay;
        Button btnDoQuest;
        RecyclerView.Adapter adapter;

        public QuestTypeViewHolder(@NonNull View itemView, QuestTypeAdapter adapter) {
            super(itemView);

            questTypeName  = itemView.findViewById(R.id.txt_quest_type_name);
            questTypeDescription = itemView.findViewById(R.id.txt_quest_type_desc);
            questTypeDay = itemView.findViewById(R.id.txt_quest_type_day);
            btnDoQuest = itemView.findViewById(R.id.btn_do_quest);

            this.adapter = adapter;
        }
    }
}
