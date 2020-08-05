package com.bhavikateli.collab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class SpecificDiscoveryFragmentAdapter extends RecyclerView.Adapter<SpecificDiscoveryFragmentAdapter.ViewHolder> {

    public static final String TAG = "SpecificDiscoveryAdapte";
    List<Post> creatorPosts = new ArrayList<>();
    List<ParseUser> creatorUsers;
    SubTopicAdapter adapter;
    Context context;
    ParseUser user;
    List<UserPosts> allCreatorPosts;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public SpecificDiscoveryFragmentAdapter(Context context, List<ParseUser> creatorUsers, List<UserPosts> allCreatorPosts) {
        this.context = context;
        this.creatorUsers = creatorUsers;
        this.allCreatorPosts = allCreatorPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_collab_creator, parent, false);
        return new SpecificDiscoveryFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user = creatorUsers.get(position);
        holder.setIsRecyclable(false);
        holder.bind(user);
    }

    /*
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
     */

    @Override
    public int getItemCount() {
        return creatorUsers.size();
    }
/*
    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, user);
        query.setLimit(100);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    //toast the output of e
                    Log.e(TAG, "cant get objects", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "post: " + post.getDescription() + ", user: " + post.getUser().getUsername());
                }
                adapter.clear();
                creatorPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

 */

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImageSpecificDiscovery;
        TextView tvUsernameSpecificDiscovery;
        TextView tvCreatorDescriptionSpecificDiscovery;
        RecyclerView rvCreatorPostsSpecificDiscovery;
        Button btnCommentSpecificDiscovery;
        Button btnLocationSpecificDiscovery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImageSpecificDiscovery = itemView.findViewById(R.id.ivProfileImageSpecificDiscovery);
            tvUsernameSpecificDiscovery = itemView.findViewById(R.id.tvUsernameSpecificDiscovery);
            tvCreatorDescriptionSpecificDiscovery = itemView.findViewById(R.id.tvCreatorDescriptionSpecificDiscovery);
            rvCreatorPostsSpecificDiscovery = itemView.findViewById(R.id.rvCreatorPostsSpecificDiscovery);
            btnCommentSpecificDiscovery = itemView.findViewById(R.id.btnCommentSpecificDiscovery);
            btnLocationSpecificDiscovery = itemView.findViewById(R.id.btnLocationSpecificDiscovery);
        }

        public void bind(final ParseUser user) {

            for(UserPosts userPosts: allCreatorPosts){
                if(userPosts.user == user){
                    creatorPosts = userPosts.creatorPosts;
                }
            }

            ParseFile profileImage = user.getParseFile("profilePicture");

            Glide.with(context)
                    .load(profileImage.getUrl())
                    .transform(new RoundedCornersTransformation(100, 20))
                    .into(ivProfileImageSpecificDiscovery);

            tvUsernameSpecificDiscovery.setText(user.getUsername());

            tvCreatorDescriptionSpecificDiscovery.setText(user.get("profileDescription").toString());

            btnLocationSpecificDiscovery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MapActivity.class);
                    intent.putExtra("user", Parcels.wrap(user));
                    context.startActivity(intent);
                }
            });

            btnCommentSpecificDiscovery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CommentActivity.class);
                    intent.putExtra("user", Parcels.wrap(user));
                    context.startActivity(intent);
                }
            });

            adapter = new SubTopicAdapter(context, creatorPosts);


            rvCreatorPostsSpecificDiscovery.setAdapter(adapter);

            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

            rvCreatorPostsSpecificDiscovery.setLayoutManager(manager);

            rvCreatorPostsSpecificDiscovery.setRecycledViewPool(viewPool);

        }
    }
}
