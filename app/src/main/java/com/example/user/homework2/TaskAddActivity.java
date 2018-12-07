package com.example.user.homework2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class TaskAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        Button addSong = (Button)findViewById(R.id.addMS);
        addSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String song_title = ((EditText)findViewById(R.id.nameSong)).getText().toString();
                String song_artist = ((EditText)findViewById(R.id.nameArtist)).getText().toString();
                String song_releaseDate = ((EditText)findViewById(R.id.releaseDate)).getText().toString();

                if(song_title.isEmpty() && song_artist.isEmpty() && song_releaseDate.isEmpty()){
                    song_title=getResources().getString(R.string.default_song);
                    song_artist=getResources().getString(R.string.default_artist);
                    song_releaseDate=getResources().getString(R.string.default_date);
                } else {
                    if(song_title.isEmpty()){
                        song_title=getResources().getString(R.string.default_song);
                    }
                    if(song_artist.isEmpty()){
                        song_artist=getResources().getString(R.string.default_artist);
                    }
                    if(song_releaseDate.isEmpty()){
                        song_releaseDate=getResources().getString(R.string.default_date);
                    }


                }

                Task tmp = new Task(song_title, song_artist, song_releaseDate);
                MainActivity.myTasks.add(tmp);

                ((EditText)findViewById(R.id.nameSong)).setText(null);
                ((EditText)findViewById(R.id.nameArtist)).setText(null);
                ((EditText)findViewById(R.id.releaseDate)).setText(null);

                View focusedView = getCurrentFocus();
                if(focusedView!=null){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(focusedView.getWindowToken(),0);
                }

                finish();

            }
        });
    }
}
