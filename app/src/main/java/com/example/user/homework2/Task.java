package com.example.user.homework2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

class Task implements Parcelable {
    public String Song_title;
    public String Artist_name;
    public String release_date;
    public String picPath;

    Task(){
    }
    ;

    Task(String t, String a){
        Song_title = t;
        Artist_name = a;
        release_date = "";
        picPath = null;
    }
    Task(String t, String a, String dt){
        Song_title = t;
        Artist_name = a;
        release_date = dt;
        picPath = null;
    }
    Task(String t, String a, String dt, String pic){
        Song_title = t;
        Artist_name = a;
        release_date = dt;
        picPath = pic;
    }

    protected Task(Parcel in) {
        Song_title = in.readString();
        Artist_name = in.readString();
        release_date = in.readString();
        picPath = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public String toString(){
        return Song_title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Song_title);
        dest.writeString(Artist_name);
        dest.writeString(release_date);
        dest.writeString(picPath);
    }
}

