package com.example.user.homework2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskInfoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        TextView nameSong=(TextView)findViewById(R.id.titleSongTextView);
        TextView artist=(TextView)findViewById(R.id.ArtNameTextView);
        TextView age= (TextView)findViewById(R.id.ageTextView);

        final Task tmp = getIntent().getExtras().
                getParcelable(MainActivity.taskExtra);
        final String s= tmp.Song_title;
        final String s2 =tmp.Artist_name;
        final String s3 =tmp.release_date;

        age.setText(s3);
        if(s3.equals("")==true){age.setText("nothing");}
        nameSong.setText(s);
        artist.setText(s2);

        final FloatingActionButton Fab = (FloatingActionButton) findViewById(R.id.deleteSong);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();

                Task tmp = intent.getExtras()

                MainActivity.myTasks.remove(tmp);
                /*
                for (int i = 0; i <MainActivity.myTasks.size() ; i++) {
                    if (MainActivity.myTasks.get(i).Song_title.equals(s)&&MainActivity.myTasks.get(i).Artist_name.equals(s2)&&MainActivity.myTasks.get(i).release_date.equals(s3)){
                        MainActivity.myTasks.remove(i);
                    }
                }*/
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("remove","remove");
                startActivity(intent);
            }
        });
    }


}
