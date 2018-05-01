package com.day0ff.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class entity to store information about Users.
 */
@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "user_name")}
)
public class Users {
    /**
     * property - of Users id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;
    /**
     * property - of Users user name
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * property - of Users password
     */
    @Column(name = "password")
    private String password;
    /**
     * property - of Users enabled
     */
    @Column(name = "enabled")
    private Boolean enabled;
    /**
     * property - of Users Roles list
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private List<Roles> roles = new ArrayList<>();
    /**
     * Class constructor. Creates a new default object Users
     */
    public Users() {
    }
    /**
     * Class constructor. Creates a new object Users with the specified values
     *
     * @param userName  - Users user name
     * @param password  - Users password
     * @param enabled  - Users enabled
     */
    public Users(String userName, String password, Boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
