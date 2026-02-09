package com.tutorial.mysql.repo;

import com.tutorial.mysql.model.UserMysql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMysqlRepository extends JpaRepository<UserMysql, Integer> {

    List<UserMysql> findByLastName(String lastName);

    List<UserMysql> findByCity(String city);
}
