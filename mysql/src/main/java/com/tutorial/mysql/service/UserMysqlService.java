package com.tutorial.mysql.service;

import com.tutorial.mysql.model.UserAddress;
import com.tutorial.mysql.model.UserMysql;
import com.tutorial.mysql.repo.UserAddressRepository;
import com.tutorial.mysql.repo.UserMysqlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserMysqlService {

    private final UserMysqlRepository userMysqlRepository;
    private final UserAddressRepository userAddressRepository;

    public UserMysqlService(UserMysqlRepository userMysqlRepository, UserAddressRepository userAddressRepository) {
        this.userMysqlRepository = userMysqlRepository;
        this.userAddressRepository = userAddressRepository;
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

    public List<UserAddress> getAddressesByUserId(Integer personId) {
        return userAddressRepository.findByUserPersonId(personId);
    }

    public UserAddress addAddressToUser(Integer personId, UserAddress address) {
        UserMysql user = userMysqlRepository.findById(personId).orElse(null);
        if (user == null) {
            return null;
        }
        address.setUser(user);
        return userAddressRepository.save(address);
    }

    /**
     * Deletes an address by its ID in an idempotent manner.
     * This operation is safe to call even if the address doesn't exist.
     *
     * @param addressId the ID of the address to delete
     * @return true if the address was found and deleted, false if it didn't exist
     */
    @Transactional
    public boolean deleteAddress(Integer addressId) {
        Long deletedCount = userAddressRepository.deleteByAddressId(addressId);
        return deletedCount > 0;
    }
}
