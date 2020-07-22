package com.bhavikateli.collab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class SubTopicAdapter extends RecyclerView.Adapter<SubTopicAdapter.ViewHolder> {

    Context context;
    List<Post> creatorPosts;

    public SubTopicAdapter(Context context, List<Post> creatorPosts) {
        this.context = context;
        this.creatorPosts = creatorPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subtopic, parent, false);
        return new SubTopicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Post post = creatorPosts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return creatorPosts.size();
    }

    public void clear() {
        creatorPosts.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivSubtopicImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSubtopicImage = itemView.findViewById(R.id.ivSubtopicImage);
        }

        public void bind(Post post) {
            Log.i("SubtopicAdapter", "user of post: " + post.getUser().getUsername());
            ParseFile image = post.getImage();
            Glide.with(context).load(image.getUrl()).into(ivSubtopicImage);


        }
    }
}
