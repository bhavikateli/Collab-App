package com.bhavikateli.collab.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavikateli.collab.DiscoveryFragmentAdapter;
import com.bhavikateli.collab.R;
import com.bhavikateli.collab.Topic;

import java.util.ArrayList;
import java.util.List;


public class DiscoveryFragment extends Fragment {

    List<Topic> topics;
    List<Topic> selectedArray;
    private RecyclerView rvTopics;
    private DiscoveryFragmentAdapter adapter;
    private GridLayoutManager manager;
    private Button btnSubmitDiscovery;
    private ButtonSubmitDiscoveryListener listener;

    public DiscoveryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  ((AppCompatActivity) getActivity()).getSupportActionBar().show();

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
        btnSubmitDiscovery = view.findViewById(R.id.btnSubmitDiscovery);

        topics = new ArrayList<>();
        manager = new GridLayoutManager(getContext(), 2);
        rvTopics.setLayoutManager(manager);


        Log.i("DiscoveryFragment", "Size of topics before initializing: " + topics.size());
        topics.add(new Topic(R.drawable.crafting, false, "crafting"));
        topics.add(new Topic(R.drawable.drama, false, "drama"));
        topics.add(new Topic(R.drawable.drawing, false, "drawing"));
        topics.add(new Topic(R.drawable.fashion, false, "fashion"));
        topics.add(new Topic(R.drawable.painting, false, "painting"));
        topics.add(new Topic(R.drawable.photography, false, "photo"));
        topics.add(new Topic(R.drawable.film, false, "film"));
        topics.add(new Topic(R.drawable.graphic, false, "graphic"));
        topics.add(new Topic(R.drawable.fashiondesign, false, "fashiondesign"));
        topics.add(new Topic(R.drawable.singing, false, "singing"));
        topics.add(new Topic(R.drawable.modeling, false, "modeling"));
        topics.add(new Topic(R.drawable.makeup, false, "makeup"));
        topics.add(new Topic(R.drawable.writing, false, "writing"));
        topics.add(new Topic(R.drawable.poetry, false, "poetry"));
        topics.add(new Topic(R.drawable.computermodel, false, "computermodel"));

        Log.i("DiscoveryFragment", "Size of topics after initializing: " + topics.size());



        adapter = new DiscoveryFragmentAdapter(getContext(), topics);
        rvTopics.setAdapter(adapter);

        selectedArray = new ArrayList<>();

        btnSubmitDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Topic topic : topics) {
                    Boolean selected = topic.getSelected();
                    if (selected == true) {
                        selectedArray.add(topic);
                    }
                }

                //switch to the next fragment and send in selected
                Fragment fragment = new SpecificDiscoveryFragment();
                FragmentManager fragmentManager = getFragmentManager();
                //Replace intent with Bundle and put it in the transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.flContainer, fragment);
                fragmentTransaction.replace(R.id.flContainer, fragment);
                fragmentTransaction.commit();

            }
        });
    }


    public interface ButtonSubmitDiscoveryListener {
        void switchFragment();
    }

    public void onResumeAction() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    public void onStopAction() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}