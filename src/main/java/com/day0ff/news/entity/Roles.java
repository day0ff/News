package com.day0ff.news.entity;

import javax.persistence.*;

/**
 * The class entity to store information about persons Roles.
 */
@Entity
@Table(name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role"})}
)
public class Roles {
    /**
     * property - of Roles id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;
    /**
     * property - of Roles role
     */
    @Column(name = "role")
    private String role;
    /**
     * Class constructor. Creates a new default object Roles
     */
    public Roles(){}
    /**
     * Class constructor. Creates a new object Roles with the specified values
     *
     * @param role  - Roles role
     */
    public Roles(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", role='" + role + '\''+
                '}';
    }
}
