package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile_prog.habit_quest.R;

import java.util.Vector;

import models.Quest;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.QuestViewHolder> {

    LayoutInflater mInflater;
    Vector<Quest> quests;
    Context context;

    public QuestAdapter(Context context, Vector<Quest> quests){
        mInflater = LayoutInflater.from(context);
        this.quests = quests;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.quest_item, parent, false);
        return new QuestViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestViewHolder holder, int position) {

        Quest currentQuest = quests.get(position);
        holder.questName.setText(currentQuest.name);
        holder.questIsDone.setText("Not Done");
        holder.btnDoNow.setTag(position);

        holder.btnDoNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent ke detail quest
            }
        });
    }

    @Override
    public int getItemCount() {
        return quests.size();
    }

    class QuestViewHolder extends RecyclerView.ViewHolder {

        TextView questName, questIsDone;
        Button btnDoNow;
        RecyclerView.Adapter adapter;

        public QuestViewHolder(@NonNull View itemView, QuestAdapter adapter) {
            super(itemView);

            questName = itemView.findViewById(R.id.txt_quest_name);
            questIsDone = itemView.findViewById(R.id.txt_quest_is_done);
            btnDoNow = itemView.findViewById(R.id.btn_do_now);

            this.adapter = adapter;
        }
    }
}