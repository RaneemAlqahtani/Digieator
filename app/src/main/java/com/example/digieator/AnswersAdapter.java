package com.example.digieator;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.digieator.database.model.Answer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AnswersAdapter extends RecyclerView.Adapter<com.example.digieator.AnswersAdapter.MyViewHolder> {

    private Context context;
    private List<Answer> answersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView answer;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            answer = view.findViewById(R.id.answer);
            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }


    public AnswersAdapter(Context context, List<Answer> answersList) {
        this.context = context;
        this.answersList = answersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Answer answer = answersList.get(position);

        holder.answer.setText(answer.getAnswer());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(answer.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return answersList.size();
    }


    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
}// end of AnswersAdapter class
