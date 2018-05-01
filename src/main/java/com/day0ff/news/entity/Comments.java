package com.day0ff.news.entity;

import javax.persistence.*;

/**
 * The class entity to store information about news Comments.
 */
@Entity
@Table(name = "comments")
public class Comments {
    /**
     * property - of Comment id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;
    /**
     * property - of Comment reference to Persons
     */
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Persons person;
    /**
     * property - of Comment reference to News
     */
    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;
    /**
     * property - of Comment comment
     */
    @Column(name = "comment")
    private String comment;
    /**
     * Class constructor. Creates a new default object Comments
     */
    public Comments(){
    }
    /**
     * Class constructor. Creates a new object Comments with the specified values
     *
     * @param person  - Comments person
     * @param news  - Comments news
     * @param comment  - Comments comment
     */
    public Comments(Persons person, News news, String comment) {
        this.person = person;
        this.news = news;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", person=" + person.getFirstName() + " " + person.getLastName() +
                ", news=" + news.getTitle() +
                ", comment='" + comment + '\'' +
                '}';
    }
}
