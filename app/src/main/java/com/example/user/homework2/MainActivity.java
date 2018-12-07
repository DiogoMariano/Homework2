package com.example.user.homework2;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String taskExtra = "Task";
    private int listItemPosition = -1;


    static public ArrayList<Task> myTasks;

    //Initialize Default values
    static {
        myTasks=new ArrayList<Task>();
        myTasks.add(new Task("Song1", "artist1", "2018/12/08"));
        myTasks.add(new Task("Song2", "artist2", "2018/12/08"));
        myTasks.add(new Task("Song3", "artist3", "2018/12/08"));
        myTasks.add(new Task("Song4", "artist1", "2018/10/08"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TaskListFragment taskFr = (TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment);
        final FloatingActionButton FabDel = (FloatingActionButton) findViewById(R.id.deleteSong);
        final FloatingActionButton addFab = (FloatingActionButton) findViewById(R.id.addSong);

        final ArrayAdapter<Task> taskAdapter = (ArrayAdapter<Task>) taskFr.getListAdapter();


        taskFr.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Song Selected!", Toast.LENGTH_LONG).show();

                    listItemPosition=position; //Identify the song

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    TaskInfoFragment frag = (TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment);

                    frag.displayTask((Task)parent.getItemAtPosition(position));
                    FabDel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(listItemPosition != -1){
                                myTasks.remove(listItemPosition);

                                TaskListFragment taskFr = (TaskListFragment)getSupportFragmentManager().findFragmentById(R.id.taskFragment);
                                ArrayAdapter<Task> taskAdapter = (ArrayAdapter<Task>) taskFr.getListAdapter();
                                taskAdapter.notifyDataSetChanged();

                            }

                        }
                    });

                } else {
                    startSecondActivity(parent, position);

                }
            }
        });

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "More?", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TaskAddActivity.class);
                startActivity(intent);
            }
        });

    }

    private void startSecondActivity(AdapterView<?> parent, int position) {

        Intent intent = new Intent(this, TaskInfoActivity.class);

        Task tmp = (Task)parent.getItemAtPosition(position);

        intent.putExtra(taskExtra, tmp);
        startActivity(intent);
    }
}
