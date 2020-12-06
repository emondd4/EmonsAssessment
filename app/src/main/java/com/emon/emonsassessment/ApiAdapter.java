package com.emon.emonsassessment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emon.emonsassessment.Activities.ItemDetailsActivity;

import java.util.ArrayList;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ViewHolder> {

    public ArrayList<Post> arrayList;
    public Context context;

    public ApiAdapter(ArrayList<Post> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ApiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiAdapter.ViewHolder holder, int position) {

        holder.bind(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView score,name,language;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            score = itemView.findViewById(R.id.recycler_score);
            name = itemView.findViewById(R.id.recycler_name);
            language = itemView.findViewById(R.id.recycler_language);
        }

        public void bind(Post post) {
            score.setText(String.valueOf(post.getScore()));
            name.setText(String.valueOf(post.getShow().getName()));
            language.setText(String.valueOf(post.getShow().getLanguage()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Clicked " + getAdapterPosition() , Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(context, ItemDetailsActivity.class);
                    i.putExtra("id",String.valueOf(post.getShow().getId()));
                    i.putExtra("url",post.getShow().getUrl());
                    i.putExtra("type",post.getShow().getType());
                    i.putExtra("language",post.getShow().getLanguage());
                    i.putExtra("status",post.getShow().getStatus());
                    i.putExtra("premiered",post.getShow().getPremiered());
                    context.startActivity(i);
                }
            });
        }

    }
}
