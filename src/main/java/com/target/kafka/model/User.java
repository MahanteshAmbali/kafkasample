package com.target.kafka.model;

public class User {

    private int id;
    private String username;
    private String location;

    public User(int id, String username, String location) {
        this.id = id;
        this.username = username;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
