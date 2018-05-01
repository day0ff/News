package com.day0ff.news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The class entity to store information about News.
 */
@Entity
@Table(name = "news")
public class News {
    /**
     * property - of News id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;
    /**
     * property - of News reference to Persons
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author")
    private Persons person;
    /**
     * property - of News title
     */
    @Column(name = "title")
    private String title;
    /**
     * property - of News article
     */
    @Column(name = "article")
    private String article;
    /**
     * property - of News post
     */
    @Column(name = "post")
    private String post;
    /**
     * property - of News image
     */
    @Column(name = "image")
    private String image;
    /**
     * property - of News publication date
     */
    @Column(name = "publication_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date publicationDate;
    /**
     * property - of News views
     */
    @Column(name = "views")
    private Integer views;
    /**
     * property - of News publication
     */
    @Column(name = "published")
    private Boolean published;
    /**
     * property - of News Categories list
     */
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_categories",
            joinColumns = {@JoinColumn(name = "news_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    @JsonIgnore
    private List<Categories> categories = new ArrayList<>();
    /**
     * property - of News Tags list
     */
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_tags",
            joinColumns = {@JoinColumn(name = "news_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    @JsonIgnore
    private List<Tags> tags = new ArrayList<>();
    /**
     * property - of News Comments list
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "news")
    private List<Comments> comments = new ArrayList<>();
    /**
     * Class constructor. Creates a new default object News
     */
    public News() {
    }
    /**
     * Class constructor. Creates a new object News with the specified values
     *
     * @param person  - News person
     * @param title  - News title
     * @param article  - News article
     * @param post  - News post
     * @param image  - News image
     * @param publicationDate  - News publication date
     * @param views  - News views
     * @param published  - News published
     */
    public News(Persons person, String title, String article, String post, String image, Date publicationDate, Integer views, Boolean published) {
        this.person = person;
        this.title = title;
        this.article = article;
        this.post = post;
        this.image = image;
        this.publicationDate = publicationDate;
        this.views = views;
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

    public void setPublicationDateString(String publicationDate) {
        SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.publicationDate = yyyyMMddFormat.parse(publicationDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
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
        SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "News{" +
                "id=" + id +
                ", person=" + person +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", post='" + post + '\'' +
                ", image='" + image + '\'' +
                ", publicationDate=" + yyyyMMddFormat.format(publicationDate) +
                ", views=" + views +
                ", published=" + published +
                '}';
    }
}
