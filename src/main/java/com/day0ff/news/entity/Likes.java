package com.day0ff.news.entity;

import javax.persistence.*;

/**
 * The class entity to store information about news Likes.
 */
@Entity
@Table(name = "likes")
public class Likes {
    /**
     * property - of Likes id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;
    /**
     * property - of Likes reference to News
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_id")
    private News news;
    /**
     * property - of Likes reference to Persons
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "person_id")
    private Persons person;
    /**
     * Class constructor. Creates a new default object Likes
     */
    public Likes(){
    }
    /**
     * Class constructor. Creates a new object Likes with the specified values
     *
     * @param person  - Likes person
     * @param news  - Likes news
     */
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
