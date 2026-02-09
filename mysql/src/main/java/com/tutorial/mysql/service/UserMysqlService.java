package com.tutorial.mysql.service;

import com.tutorial.mysql.model.UserMysql;
import com.tutorial.mysql.repo.UserMysqlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMysqlService {

    private final UserMysqlRepository userMysqlRepository;

    public UserMysqlService(UserMysqlRepository userMysqlRepository) {
        this.userMysqlRepository = userMysqlRepository;
    }

    public List<UserMysql> getAllUsers() {
        return userMysqlRepository.findAll();
    }

    public UserMysql getUserById(Integer id) {
        return userMysqlRepository.findById(id).orElse(null);
    }

    public List<UserMysql> getUsersByLastName(String lastName) {
        return userMysqlRepository.findByLastName(lastName);
    }

    public List<UserMysql> getUsersByCity(String city) {
        return userMysqlRepository.findByCity(city);
    }

    public UserMysql saveUser(UserMysql user) {
        return userMysqlRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userMysqlRepository.deleteById(id);
    }
}
