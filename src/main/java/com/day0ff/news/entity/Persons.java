package com.day0ff.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class entity to store information about Persons.
 */
@Entity
@Table(name = "persons")
public class Persons {
    /**
     * property - of Persons id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;
    /**
     * property - of Persons reference to Users
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private Users user;
    /**
     * property - of Persons First Name
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * property - of Persons Last Name
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * property - of Persons Screen Name
     */
    @Column(name = "screen_name")
    private String screenName;
    /**
     * property - of Persons Image
     */
    @Column(name = "image")
    private String image;
    /**
     * property - of Persons Comments list
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Comments> comments = new ArrayList<>();
    /**
     * Class constructor. Creates a new default object Persons
     */
    public Persons(){
    }
    /**
     * Class constructor. Creates a new object Persons with the specified values
     *
     * @param user  - Persons user
     * @param firstName  - Persons first name
     * @param lastName  - Persons last name
     * @param screenName  - Persons screen name
     * @param image  - Persons image
     */
    public Persons(Users user, String firstName, String lastName, String screenName, String image) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.screenName = screenName;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", user=" + user.getUserName() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", screenName='" + screenName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
