package com.tutorial.repo;

import com.tutorial.model.mysql.UserMysql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMysqlRepository extends JpaRepository<UserMysql, Integer> {

    List<UserMysql> findByLastName(String lastName);

    List<UserMysql> findByCity(String city);
}