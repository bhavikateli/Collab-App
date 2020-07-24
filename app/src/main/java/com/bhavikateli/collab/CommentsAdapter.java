package com.bhavikateli.collab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseUser;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    Context context;
    List<Comment> comments;
    ParseUser user;

    public CommentsAdapter(Context context, List<Comment> comments, ParseUser user) {
        this.context = context;
        this.comments = comments;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvUsernameComment;
        ImageView ivProfilePictureComment;
        TextView tvComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsernameComment = itemView.findViewById(R.id.tvUsernameComment);
            tvComment = itemView.findViewById(R.id.tvComment);
            ivProfilePictureComment = itemView.findViewById(R.id.ivProfilePictureComment);
        }

        public void bind(Comment comment) {
            tvComment.setText(comment.getComment());
            tvUsernameComment.setText(comment.getUserC().getUsername());
            Log.i("CommentsAdapter", "this is user: " + comment.getUserC().toString());

            //  Log.i("CommentsAdapter", "this is profile: " + comment.getUser().getParseFile("profileImage").toString());
            // Log.i("CommentsAdapter", "this is profile: " + comment.getUser().getParseFile("profileImage").getUrl());



            Glide.with(context)
                    .load(comment.getUserC().getParseFile("profilePicture").getUrl())
                    .transform(new RoundedCornersTransformation(40, 25))
                    .into(ivProfilePictureComment);


            }
        }
    }

