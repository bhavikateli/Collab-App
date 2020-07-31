package com.bhavikateli.collab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> {

    public interface UsernameListener {
        void switchFragment(String fragmentName, Post post);
    }

    private Context context;
    private List<Post> posts;
    Post post;

    public HomeFragmentAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvUsernameHome;
        ImageView ivPostHome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPostHome = itemView.findViewById(R.id.ivPostHome);
            tvUsernameHome = itemView.findViewById(R.id.tvUsernameHome);
        }
        public void bind(final Post post) {
            tvUsernameHome.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            Glide.with(context).load(image.getUrl()).into(ivPostHome);

            tvUsernameHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)context).switchFragment("UserFragment", post);
                }
            });
        }
    }
}
