package com.example.listviewdemo;

class Repository {

    private String name;

    private String owner;

    public Repository(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }
}
