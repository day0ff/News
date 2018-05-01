package com.day0ff.news.entity;

import javax.persistence.*;

/**
 * The class entity to store information about news Tags.
 */
@Entity
@Table(name = "tags")
public class Tags {
    /**
     * property - of Tags id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;
    /**
     * property - of Tags tag
     */
    @Column(name = "tag")
    private String tag;
    /**
     * Class constructor. Creates a new default object Tags
     */
    public Tags() {
    }
    /**
     * Class constructor. Creates a new object Tags with the specified values
     *
     * @param tag  - Tags tag
     */
    public Tags(String tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }
}
