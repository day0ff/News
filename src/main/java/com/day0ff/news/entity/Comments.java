package com.day0ff.news.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Persons person;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Column(name = "comment")
    private String comment;

    public Comments(){
    }

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
