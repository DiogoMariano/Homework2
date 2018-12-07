package com.example.user.homework2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
