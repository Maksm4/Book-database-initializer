package org.tpo4_1;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Set;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER)
    private Set<Article> articles;

    @OneToOne(fetch = FetchType.EAGER)
    private User manager;


    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public User getManager() {
        return manager;
    }

    @Transactional
    @Modifying
    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                ", manager=" + manager;
    }
}
