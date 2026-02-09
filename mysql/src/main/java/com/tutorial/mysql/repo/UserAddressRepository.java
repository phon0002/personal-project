package com.tutorial.mysql.repo;

import com.tutorial.mysql.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

    List<UserAddress> findByUserPersonId(Integer personId);

    @Modifying
    Long deleteByAddressId(Integer addressId);
}
