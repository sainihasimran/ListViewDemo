package com.example.listviewdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Repository implements Parcelable {

    private String name;

    private String owner;

    private String description;

    private boolean fork;

    private boolean siteAdmin;

    public Repository(String name, String owner, String description, boolean fork, boolean siteAdmin) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fork = fork;
        this.siteAdmin = siteAdmin;
    }

    protected Repository(Parcel in) {
        this.name = in.readString();
        this.owner = in.readString();
        this.description = in.readString();
        this.fork = in.readByte() != 0;
        this.siteAdmin = in.readByte() != 0;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFork() {
        return fork;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(owner);
        dest.writeString(description);
        dest.writeByte((byte) (fork ? 1 : 0));
        dest.writeByte((byte) (siteAdmin ? 1 : 0));
    }

    public static final Parcelable.Creator<Repository> CREATOR
            = new Parcelable.Creator<Repository>() {
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}
