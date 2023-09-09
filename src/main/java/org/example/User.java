package org.example;


import java.util.Date;

public class User {
    private long id;
    private String userName;
    private String password;
    private String singUp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSingUp() {
        return singUp;
    }

    public void setSingUp(String singUp) {
        this.singUp = singUp;
    }

    public User(String userName, String password, String singUp) {
        this.userName = userName;
        this.password = password;
        this.singUp = singUp;
    }

    public User() {
    }

    public User(long id, String userName, String password, String singUp) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.singUp = singUp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", singUp='" + singUp + '\'' +
                '}';
    }
}
