package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class pizza implements Serializable, Parcelable {
    String title;
    float cost;
    ArrayList<String> customization;

    pizza(String title, float cost,ArrayList<String> cus){
        this.title = title;
        this.cost = cost;
        customization = cus;
    }

    protected pizza(Parcel in) {
        title = in.readString();
        cost = in.readFloat();
        customization = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeFloat(cost);
        dest.writeStringList(customization);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<pizza> CREATOR = new Creator<pizza>() {
        @Override
        public pizza createFromParcel(Parcel in) {
            return new pizza(in);
        }

        @Override
        public pizza[] newArray(int size) {
            return new pizza[size];
        }
    };
}
