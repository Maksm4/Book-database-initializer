package org.tpo4_1;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Query("SELECT u FROM User u")
    List<User> listAllUsers();

    @Query("SELECT u FROM User u WHERE u.email = :email ")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.blog = :blog ")
    User findByBlog(Blog blog);

    @Query("DELETE FROM User u WHERE u.email = :email ")
    @Transactional
    @Modifying
    void deleteByEmail(String email);

    @Query("DELETE FROM User u WHERE u.blog = :blog ")
    @Transactional
    @Modifying
    void deleteByBlog(Blog blog);

}

