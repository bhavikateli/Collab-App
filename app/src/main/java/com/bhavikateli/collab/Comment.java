package com.bhavikateli.collab;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze={Comment.class})
@ParseClassName("Comment")
public class Comment extends ParseObject {
    public Comment(){ }

    public static final String KEY_USER = "user";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_USER_ROOT_POINTER = "userRootPointer";

    public ParseUser getUserC() {
        return getParseUser(KEY_USER);
    }

    public void setUserC(ParseUser user) {
        put(KEY_USER, user);
    }

    public String getComment(){
        return getString(KEY_CONTENT);
    }

    public void setComment(String content){
        put(KEY_CONTENT, content);
    }

    public void  setUserRootComment(ParseUser user){
        put(KEY_USER_ROOT_POINTER, user);
    }

    public ParseUser getUserRootComment(){
        return getParseUser(KEY_USER_ROOT_POINTER);
    }
}
