package org.tpo4_1;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog,Long> {
    @Query("SELECT b FROM Blog b")
    List<Blog> listAllBlogs();

    @Query("SELECT b FROM Blog b WHERE b.name = :name")
    Blog findByName(String name);
    Blog searchBlogByManager(User manager);

    @Query("DELETE FROM Blog b WHERE b.manager = :manager")
    @Transactional
    @Modifying
    void deleteByManager(User manager);

}
