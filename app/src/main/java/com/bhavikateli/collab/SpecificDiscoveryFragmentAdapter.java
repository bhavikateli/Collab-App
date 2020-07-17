package com.bhavikateli.collab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;

import java.util.List;

public class SpecificDiscoveryFragmentAdapter extends RecyclerView.Adapter<SpecificDiscoveryFragmentAdapter.ViewHolder> {

    Context context;
    List<ParseUser> creatorUsers;

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

    }

    @Override
    public int getItemCount() {
        return creatorUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
