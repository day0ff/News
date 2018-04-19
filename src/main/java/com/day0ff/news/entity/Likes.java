package com.day0ff.news.entity;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_id")
    private News news;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "person_id")
    private Persons person;

    public Likes(){
    }

    public Likes(News news, Persons person) {
        this.news = news;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", news=" + news +
                ", person=" + person +
                '}';
    }
}
