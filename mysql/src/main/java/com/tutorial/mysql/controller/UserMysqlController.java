package com.tutorial.mysql.controller;

import com.tutorial.mysql.model.UserAddress;
import com.tutorial.mysql.model.UserMysql;
import com.tutorial.mysql.service.UserMysqlService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mysql/users")
public class UserMysqlController {

    private final UserMysqlService userMysqlService;

    public UserMysqlController(UserMysqlService userMysqlService) {
        this.userMysqlService = userMysqlService;
    }

    @GetMapping
    public List<UserMysql> getAllUsers() {
        return userMysqlService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserMysql getUserById(@PathVariable Integer id) {
        return userMysqlService.getUserById(id);
    }

    @GetMapping("/by-last-name/{lastName}")
    public List<UserMysql> getUsersByLastName(@PathVariable String lastName) {
        return userMysqlService.getUsersByLastName(lastName);
    }

    @GetMapping("/by-city/{city}")
    public List<UserMysql> getUsersByCity(@PathVariable String city) {
        return userMysqlService.getUsersByCity(city);
    }

    @PostMapping
    public UserMysql saveUser(@RequestBody UserMysql user) {
        return userMysqlService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userMysqlService.deleteUser(id);
    }

    @GetMapping("/{id}/addresses")
    public List<UserAddress> getAddressesByUserId(@PathVariable Integer id) {
        return userMysqlService.getAddressesByUserId(id);
    }

    @PostMapping("/{id}/addresses")
    public UserAddress addAddressToUser(@PathVariable Integer id, @RequestBody UserAddress address) {
        return userMysqlService.addAddressToUser(id, address);
    }

    @DeleteMapping("/addresses/{addressId}")
    public void deleteAddress(@PathVariable Integer addressId) {
        userMysqlService.deleteAddress(addressId);
    }
}
