package com.day0ff.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author")
    private Persons person;

    @Column(name = "title")
    private String title;

    @Column(name = "article")
    private String article;

    @Column(name = "post")
    private String post;

    @Column(name = "image")
    private String image;

    @Column(name = "publication_date")
    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @Column(name = "views")
    private Integer views;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "published")
    private Boolean published;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_tags",
            joinColumns = {@JoinColumn(name = "news_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    @JsonIgnore
    private List<Tags> tags = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "news")
    private List<Comments> comments = new ArrayList<>();

    public News() {
    }

    public News(Persons person, String title, String article, String post, String image, Date publicationDate, Integer views, Integer likes, Boolean published) {
        this.person = person;
        this.title = title;
        this.article = article;
        this.post = post;
        this.image = image;
        this.publicationDate = publicationDate;
        this.views = views;
        this.likes = likes;
        this.published = published;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", person=" + person +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", post='" + post + '\'' +
                ", image='" + image + '\'' +
                ", publicationDate=" + publicationDate +
                ", views=" + views +
                ", likes=" + likes +
                ", published=" + published +
                '}';
    }
}
