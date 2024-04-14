package org.tpo4_1;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {

    @Query("SELECT r FROM Role r")
    List<Role> listAllRoles();

    @Query("SELECT r FROM Role r WHERE r.name = :name ")
    Role findAllByName(String name);



    @Query("DELETE FROM Role r WHERE r.name = :name ")
    @Transactional
    @Modifying
    void deleteAllByName(String name);

    @Query("DELETE FROM Role r WHERE r.id = :id")
    @Transactional
    @Modifying
    void deleteAllById(Long id);

}
