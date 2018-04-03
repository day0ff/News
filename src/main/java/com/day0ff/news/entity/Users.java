package com.day0ff.news.entity;

import javax.persistence.*;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "user_name")}
)
public class Users {
    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    public Users() {
    }

    public Users(String userName, String password, Boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
