package com.bhavikateli.collab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class DiscoveryFragmentAdapter extends RecyclerView.Adapter<DiscoveryFragmentAdapter.ViewHolder> {

    public static final String TAG = "DiscoveryFragmentAdapte";
    Context context;
    List<Topic> topics;
    //ImageView ivTopic;
    RequestOptions requestOptions;

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
        //int pos = position;
        Topic top = topics.get(position);
        Log.i(TAG, "position inside bindviewholder: " + position);
        /*Log.i(TAG, "topic size: " + topics.size());
        for (int i = 0; i < topics.size(); i++)
        {
            Log.i(TAG, "topic contents at index : " + i + " " + topics.get(i).getName());
        }*/

        holder.bind(top);
        //holder.bind(pos);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivTopic;
        ImageView ivCheck;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivTopic = itemView.findViewById(R.id.ivTopic);
            ivCheck = itemView.findViewById(R.id.ivCheck);
        }

        public void bind(final Topic topic) {
            // final Topic topic = topics.get(pos);

            Glide.with(context).load(topic.getImg()).apply(requestOptions).into(ivTopic);


            Log.i(TAG, "topic inside bind: " + topic.getName());


            /*for (int i = 0; i < topics.size(); i++)
            {
                Log.i(TAG, "topic contents inside bind method at index : " + i + " " + topics.get(i).getName());
            }*/

            ivTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Boolean selected = topic.getSelected();
                    Log.i(TAG, "selectedState before: " + topic.getSelected() + "imageName " + topic.getName() );
                    selected = !selected;
                    topic.setSelected(selected);
                    Log.i(TAG, "selectedState after: " + topic.getSelected() +  "imageName " + topic.getName());
                    if (selected == true) {
                        //ivTopic.setImageDrawable(context.getResources().getDrawable(R.drawable.bhavi));
                       // Glide.with(context).load(R.drawable.bhavi).apply(requestOptions).into(ivTopic);
                        ivCheck.setVisibility(View.VISIBLE);
                    } else if (selected == false){
                        ivCheck.setVisibility(View.INVISIBLE);

                    }
                }
            });

        }
    }
}
