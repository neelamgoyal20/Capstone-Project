package com.learn.teleprompter.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by E01090 on 7/12/2016.
 */
public class Script implements Parcelable {

    public int id;
    public String title;
    public String content;
    public long date;

    public Script() {
    }

    public Script(String title, String text, long date) {
        this.title = title;
        this.content = text;
        this.date = date;
    }

    protected Script(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        date = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeLong(date);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Script> CREATOR = new Parcelable.Creator<Script>() {
        @Override
        public Script createFromParcel(Parcel in) {
            return new Script(in);
        }

        @Override
        public Script[] newArray(int size) {
            return new Script[size];
        }
    };
}
