package com.bhavikateli.collab.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavikateli.collab.Post;
import com.bhavikateli.collab.R;
import com.bhavikateli.collab.SpecificDiscoveryFragmentAdapter;
import com.bhavikateli.collab.UserPosts;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SpecificDiscoveryFragment extends Fragment {

    public static final String TAG = "SpecificDiscoveryFragme";
    private RecyclerView rvSpecificDiscovery;
    private List<ParseUser> creatorUsers;
    List<Post> creatorPosts = new ArrayList<>();
    private SpecificDiscoveryFragmentAdapter adapter;
    List<UserPosts> allCreatorPosts = new ArrayList<>();

    public SpecificDiscoveryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specific_discovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSpecificDiscovery = view.findViewById(R.id.rvSpecificDiscovery);

        creatorUsers = new ArrayList<>();
        adapter = new SpecificDiscoveryFragmentAdapter(getContext(), creatorUsers, allCreatorPosts);

        rvSpecificDiscovery.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvSpecificDiscovery.setLayoutManager(manager);


        queryUsers();

    }

    private void queryUsers() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    Toast.makeText(getContext(), "Query Success", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "user: " + objects.get(0).getUsername());
                    Log.i(TAG, "user: " + objects.get(1).getUsername());
                    Log.i(TAG, "user: " + objects.get(2).getUsername());
                    creatorUsers.addAll(objects);
                    adapter.notifyDataSetChanged();
                    for(ParseUser user: creatorUsers){
                        UserPosts userPosts = new UserPosts(user);
                        creatorPosts = userPosts.getCreatorPosts();
                        allCreatorPosts.add(userPosts);
                    }
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getContext(), "Query Not Successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*

    ParseQuery<ParseUser> query = ParseUser.getQuery();
query.whereEqualTo("username", name);
query.findInBackground(new FindCallback<ParseUser>() {
    public void done(List<ParseUser> objects, ParseException e) {
        if (e == null) {
            Toast.makeText(getApplicationContext(),"Query Success",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),"Query Not Successful",Toast.LENGTH_LONG).show();
        }
    }
})

    private void queryUsers() {
        ParseQuery<User> query = ParseQuery.getQuery(User.class);
        query.include(User.KEY_USERNAME);
        query.setLimit(50);
        query.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                if (e != null){
                    Log.e(TAG, "cant get objects", e);
                    return;
                }
                for(User user: users){
                    Log.i(TAG, "post: " + user.getUsername() + ", description: " + user.getProfileDescription());
                }
                creatorUsers.addAll(users);
                //adapter.notifyDataSetChanged();
            }
        });
    }
    */
   /*
    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(100);
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
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
    */
}