package com.example.user.homework2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskInfoFragment extends Fragment {


    public TaskInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }

    public void displayTask(Task task){
        ((TextView)getActivity().findViewById(R.id.titleSongTextView)).setText(task.Song_title);
        ((TextView)getActivity().findViewById(R.id.ArtNameTextView)).setText(task.Artist_name);
        ((TextView)getActivity().findViewById(R.id.ageTextView)).setText(task.release_date);

        ImageView imView = (ImageView)getActivity().findViewById(R.id.SPhoto);
        Random rnd = new Random();
        int a = rnd.nextInt(5);
        switch (a)
        {
            case 0:
                imView.setImageResource(R.drawable.et_logo);
                break;
            case 1:
                imView.setImageResource(R.drawable.face);
                break;
            case 2:
                imView.setImageResource(R.drawable.ic_launcher_background);
                break;
            case 3:
                imView.setImageResource(R.drawable.pp_logo);
                break;
            case 4:
                imView.setImageResource(R.drawable.ic_launcher_foreground);
                break;
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();

        Task receivedTask = intent.getParcelableExtra(MainActivity.taskExtra);
        if(receivedTask!=null){
            displayTask(receivedTask);
        }
    }

}
