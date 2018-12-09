package com.example.user.homework2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TASKS_FILE = "com.example.user.homework2.TasksFile";
    public static final String NUM_TASKS = "NumOfTasks";
    public static final String TASK ="task_";
    public static final String ARTIST = "artist_";
    public static final String RELEASE_DATE = "release date_";
    public static final String PIC = "pic_";

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
        restoreTasks();

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

    @Override
    public void onResume(){
        super.onResume();
        TaskListFragment taskFr = (TaskListFragment)getSupportFragmentManager().findFragmentById(R.id.taskFragment);
        ArrayAdapter<Task> taskAdapter = (ArrayAdapter<Task>) taskFr.getListAdapter();
        taskAdapter.notifyDataSetChanged();
    }

    private void startSecondActivity(AdapterView<?> parent, int position) {

        Intent intent = new Intent(this, TaskInfoActivity.class);

        Task tmp = (Task)parent.getItemAtPosition(position);

        intent.putExtra(taskExtra, tmp);
        startActivity(intent);
    }

    private boolean saveTasks(){
        SharedPreferences tasks = getSharedPreferences(TASKS_FILE, MODE_PRIVATE);

        SharedPreferences.Editor editor = tasks.edit();

        editor.clear();

        editor.putInt(NUM_TASKS, myTasks.size());

        for(Integer i = 0; i<myTasks.size(); i++){
            editor.putString(TASK + i.toString(), myTasks.get(i).Song_title);
            editor.putString(ARTIST + i.toString(), myTasks.get(i).Artist_name);
            editor.putString(RELEASE_DATE + i.toString(), myTasks.get(i).release_date);
            editor.putString(PIC + i.toString(), myTasks.get(i).picPath);
        }

        return editor.commit();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(!saveTasks()){
            Toast.makeText(getApplicationContext(), "Save failed!", Toast.LENGTH_LONG).show();
        }
        saveTasksToFile();
    }
    private void restoreTasks(){
        SharedPreferences tasks = getSharedPreferences(TASKS_FILE, MODE_PRIVATE);
        int numOfTasks = tasks.getInt("NumOfTasks", 0);
        if(numOfTasks!=0){
            myTasks.clear();
            for(Integer i=0; i<numOfTasks; i++){
                String title = tasks.getString(TASK + i.toString(), "0");
                String art = tasks.getString(ARTIST + i.toString(),"0");
                String date = tasks.getString(RELEASE_DATE + i.toString(),"0");
                String pic_path = tasks.getString(PIC + i.toString(),"");
                Task tmp = new Task(title,art,date,pic_path);

                myTasks.add(tmp);
            }
        }
    }

    private void saveTasksToFile(){
        String filename = "myTasks.txt";
        FileOutputStream outputStream;

        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputStream.getFD()));
            String delim = ";";

            for(Integer i=0; i<myTasks.size();i++){
                Task tmp = myTasks.get(i);
                String line = tmp.Song_title + delim + tmp.Artist_name + delim + tmp.release_date + delim + tmp.picPath;
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
