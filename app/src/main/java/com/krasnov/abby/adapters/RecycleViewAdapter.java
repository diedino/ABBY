package com.krasnov.abby.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.krasnov.abby.R;
import com.krasnov.abby.models.News;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    //private List<String> mTitles = new ArrayList<>();
    //private List<String> mDates = new ArrayList<>();
    private List<News> mNews = new ArrayList<>();
    private Context mContext;

    public RecycleViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public RecycleViewAdapter(Context mContext, List<News> mNews) {
        this.mNews = mNews;
        this.mContext = mContext;
    }

    public void setData( List<News> list) {
        mNews = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "OnBindViewHolder: called.");

        viewHolder.title.setText(mNews.get(i).getTitle());
        viewHolder.date.setText(mNews.get(i).getCreation_date());

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mNews.get(i).getCreation_date());

                Toast.makeText(mContext, mNews.get(i).getCreation_date(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.title);
            parentLayout = itemView.findViewById(R.id.recycle_layout);

        }
    }
}
