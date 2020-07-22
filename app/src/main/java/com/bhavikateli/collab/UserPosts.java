package com.bhavikateli.collab;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UserPosts {
    ParseUser user;
    List<Post> creatorPosts = new ArrayList<>();

    public UserPosts(ParseUser user) {
        this.user = user;
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
                if (e != null) {
                    //toast the output of e
                    Log.e("UserPosts", "cant get objects", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i("UserPosts", "post: " + post.getDescription() + ", user: " + post.getUser().getUsername());
                }
                creatorPosts.addAll(posts);

            }
        });
    }

    public List<Post> getCreatorPosts(){
        creatorPosts.clear();
        queryPosts();
        return creatorPosts;
    }
}
