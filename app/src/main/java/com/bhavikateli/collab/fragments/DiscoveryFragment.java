package com.bhavikateli.collab.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavikateli.collab.DiscoveryFragmentAdapter;
import com.bhavikateli.collab.R;

import java.util.ArrayList;
import java.util.List;


public class DiscoveryFragment extends Fragment {

    private RecyclerView rvTopics;
    private DiscoveryFragmentAdapter adapter;
    private GridLayoutManager manager;
    List<Topic> topics;

    public DiscoveryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTopics = view.findViewById(R.id.rvTopics);

        topics = new ArrayList<>();
        manager = new GridLayoutManager(getContext(), 2);
        rvTopics.setLayoutManager(manager);


        topics.add(new Topic(R.drawable.crafting, false));
        topics.add(new Topic(R.drawable.drama, false));
        topics.add(new Topic(R.drawable.drawing, false));
        topics.add(new Topic(R.drawable.fashion, false));
        topics.add(new Topic(R.drawable.painting, false));
        topics.add(new Topic(R.drawable.photography, false));
        topics.add(new Topic(R.drawable.film, false));
        topics.add(new Topic(R.drawable.graphic, false));
        topics.add(new Topic(R.drawable.fashiondesign, false));
        topics.add(new Topic(R.drawable.singing, false));
        topics.add(new Topic(R.drawable.modeling, false));
        topics.add(new Topic(R.drawable.makeup, false));
        topics.add(new Topic(R.drawable.writing, false));
        topics.add(new Topic(R.drawable.poetry, false));
        topics.add(new Topic(R.drawable.computermodel, false));


        adapter = new DiscoveryFragmentAdapter(getContext(), topics);
        rvTopics.setAdapter(adapter);


    }
}