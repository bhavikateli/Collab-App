package com.bhavikateli.collab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavikateli.collab.fragments.SubTopicAdapter;
import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SpecificDiscoveryFragmentAdapter extends RecyclerView.Adapter<SpecificDiscoveryFragmentAdapter.ViewHolder> {

    public static final String TAG = "SpecificDiscoveryAdapte";
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    List<Post> creatorPosts = new ArrayList<>();
    List<ParseUser> creatorUsers;
    SubTopicAdapter adapter;
    Context context;
    ParseUser user;

    public SpecificDiscoveryFragmentAdapter(Context context, List<ParseUser> creatorUsers) {
        this.context = context;
        this.creatorUsers = creatorUsers;
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
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return creatorUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImageSpecificDiscovery;
        TextView tvUsernameSpecificDiscovery;
        TextView tvCreatorDescriptionSpecificDiscovery;
        RecyclerView rvCreatorPostsSpecificDiscovery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImageSpecificDiscovery = itemView.findViewById(R.id.ivProfileImageSpecificDiscovery);
            tvUsernameSpecificDiscovery = itemView.findViewById(R.id.tvUsernameSpecificDiscovery);
            tvCreatorDescriptionSpecificDiscovery = itemView.findViewById(R.id.tvCreatorDescriptionSpecificDiscovery);
            rvCreatorPostsSpecificDiscovery = itemView.findViewById(R.id.rvCreatorPostsSpecificDiscovery);
        }

        public void bind(ParseUser user) {

            ParseFile profileImage = user.getParseFile("profilePicture");;
            //creatorPosts.clear();

            Log.i("SpecificDiscoveryAdapte", "pic url: " + profileImage.getUrl() );
            Glide.with(context)
                    .load(profileImage.getUrl())
                    .into(ivProfileImageSpecificDiscovery);

            tvUsernameSpecificDiscovery.setText(user.getUsername());

            tvCreatorDescriptionSpecificDiscovery.setText(user.get("profileDescription").toString());

            creatorPosts.clear();

            adapter = new SubTopicAdapter(context, creatorPosts);

            rvCreatorPostsSpecificDiscovery.setAdapter(adapter);

            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

            rvCreatorPostsSpecificDiscovery.setLayoutManager(manager);

            rvCreatorPostsSpecificDiscovery.setRecycledViewPool(viewPool);
            queryPosts();


            }
        }
    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, user);
        query.setLimit(100);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    //toast the output of e
                    Log.e(TAG, "cant get objects", e);
                    return;
                }
                for(Post post: posts){
                    Log.i(TAG, "post: " + post.getDescription() + ", user: " + post.getUser().getUsername());
                }
               creatorPosts.addAll(posts);
               adapter.notifyDataSetChanged();
            }
        });
    }
}
