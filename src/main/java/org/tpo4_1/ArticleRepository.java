package org.tpo4_1;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article,Long> {

    @Query("SELECT a FROM Article a")
    List<Article> listAllArticles();

    @Query("SELECT a FROM Article a WHERE a.author = :author")
    List<Article> findAllByAuthor(User author);

    @Query("SELECT a FROM Article a WHERE a.blog = :blog")
    List<Article> findAllByBlog(Blog blog);

    @Query("SELECT a FROM Article a WHERE a.title = :title")
    Article findByTitle(String title);


    @Query("DELETE FROM Article a WHERE a.title = :title ")
    @Transactional
    @Modifying
    void deleteByTitle(String title);

    @Query("DELETE FROM Article a WHERE a.author = :author ")
    @Transactional
    @Modifying
    void deleteAllByAuthor(User author);

    @Query("DELETE FROM Article a WHERE a.blog = :blog ")
    @Transactional
    @Modifying
    void deleteAllByBlog(Blog blog);
}

