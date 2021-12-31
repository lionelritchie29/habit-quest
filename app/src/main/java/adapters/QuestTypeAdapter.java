package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile_prog.habit_quest.QuestListActivity;
import com.mobile_prog.habit_quest.R;

import java.util.Vector;

import models.QuestType;

public class QuestTypeAdapter extends RecyclerView.Adapter<QuestTypeAdapter.QuestTypeViewHolder> {

    LayoutInflater mInflater;
    Vector<QuestType> questTypes;
    Context context;

    public QuestTypeAdapter(Context context, Vector<QuestType> questTypes){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.questTypes = questTypes;
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
        holder.questTypeName.setText(currentQuestType.name);
        holder.questTypeDescription.setText(currentQuestType.description);
        holder.questTypeDay.setText(currentQuestType.day + " days");
        holder.btnDoQuest.setTag(position);

        holder.btnDoQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position =Integer.parseInt(v.getTag().toString());
                Intent toQuestList = new Intent(context, QuestListActivity.class);
                context.startActivity(toQuestList);
            }
        });
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
