package com.bhavikateli.collab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavikateli.collab.fragments.Topic;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class DiscoveryFragmentAdapter extends RecyclerView.Adapter<DiscoveryFragmentAdapter.ViewHolder> {

    public static final String TAG = "DiscoveryFragmentAdapte";
    Context context;
    List<Topic> topics;
    ImageView ivTopic;
    RequestOptions requestOptions ;

    public DiscoveryFragmentAdapter(Context context, List<Topic> topics) {
        this.context = context;
        this.topics = topics;
        requestOptions = new RequestOptions().fitCenter();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.ivTopic.setImageDrawable(topics.get(position).getImg());
        int pos = position;
        holder.bind(pos);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivTopic = itemView.findViewById(R.id.ivTopic);
        }

        public void bind(final int pos) {
            final Topic topic = topics.get(pos);

            Glide.with(context).load(topics.get(pos).getImg()).apply(requestOptions).into(ivTopic);

            ivTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Boolean selected = topic.getSelected();
                    Log.i(TAG,  "selectedState before: " + topic.getSelected());
                    selected = !selected;
                    topic.setSelected(selected);
                    Log.i(TAG, "selectedState after: " + topic.getSelected());
                    if(selected == true){
                        //ivTopic.setImageDrawable(context.getResources().getDrawable(R.drawable.bhavi));
                        //Glide.with(context).load(R.drawable.bhavi).apply(requestOptions).into(ivTopic);
                    }
                }
            });

        }
    }
}
