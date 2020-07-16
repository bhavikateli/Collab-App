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

public class ProfileFragmentAdapter extends RecyclerView.Adapter<ProfileFragmentAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public ProfileFragmentAdapter(Context context,List<Post> posts ){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivUserImage;
        private TextView tvPostDescriptionProfile;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivUserImage = itemView.findViewById(R.id.ivUserImage);
            tvPostDescriptionProfile = itemView.findViewById(R.id.tvPostDescriptionProfile);
        }

        public void bind(Post post) {
            ParseFile image = post.getImage();

            tvPostDescriptionProfile.setText(post.getDescription());

            if(image != null){
                Glide.with(context).load(image.getUrl()).into(ivUserImage);
            }
        }
    }

}
