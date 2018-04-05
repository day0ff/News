package com.day0ff.news.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role", "user_name"})}
)
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private Users users;

    @Column(name = "role")
    private String role;

    public UserRoles(){}

    public UserRoles(Users users, String role) {
        this.users = users;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", user=" + users.getUserName() +
                ", role='" + role + '\'' +
                '}';
    }
}
