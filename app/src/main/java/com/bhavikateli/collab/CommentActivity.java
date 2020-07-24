package com.bhavikateli.collab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView rvComments;
    List<Comment> comments;
    CommentsAdapter adapter;
    Comment comment;
    public static final String TAG = "CommentsActivity";
    ParseUser user;
    Button btnComment;
    EditText etNewCommentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setCreate(user);
    }

    private void setCreate(final ParseUser user) {
        rvComments = findViewById(R.id.rvComments);
        btnComment = findViewById(R.id.btnComment);
        etNewCommentContent = findViewById(R.id.etNewCommentContent);

        comments = new ArrayList<>();
        adapter = new CommentsAdapter(this, comments, user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        rvComments.setLayoutManager(linearLayoutManager);

        rvComments.setAdapter(adapter);
        queryComment();

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Inside comment in button");
                String newComment = etNewCommentContent.getText().toString();
                ParseUser currentUser = ParseUser.getCurrentUser();

                createComment(newComment, currentUser, user);
            }
        });
    }

    private void createComment(String newComment, ParseUser currentUser, ParseUser user) {
        Comment comment = new Comment();
        comment.setUserC(currentUser);
        comment.setComment(newComment);
        comment.setUserRootComment(user);
        comment.saveInBackground();
    }

    private void queryComment() {
        ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
        query.include(Comment.KEY_USER);
        query.whereEqualTo(Comment.KEY_USER_ROOT_POINTER, user);
        query.setLimit(20);
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> Comments, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                comments.addAll(Comments);
                adapter.notifyDataSetChanged();
            }
        });
    }
}