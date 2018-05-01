package com.day0ff.news.entity;

import javax.persistence.*;

/**
 * The class entity to store information about news Categories.
 */
@Entity
@Table(name = "categories")
public class Categories {
    /**
     * property - of Category id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;
    /**
     * property - of Category name
     */
    @Column(name = "category")
    private String category;
    /**
     * Class constructor. Creates a new default object Categories
     */
    public Categories(){
    }
    /**
     * Class constructor. Creates a new object Categories with the specified values
     *
     * @param category  - Category name
     */
    public Categories(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
