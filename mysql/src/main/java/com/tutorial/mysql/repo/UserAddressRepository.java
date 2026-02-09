package com.tutorial.mysql.repo;

import com.tutorial.mysql.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

    List<UserAddress> findByUserPersonId(Integer personId);

    /**
     * Atomically deletes an address by its ID and returns the count of deleted records.
     * This method performs the existence check and deletion in a single database operation,
     * preventing race conditions and making the delete operation idempotent.
     *
     * @param addressId the ID of the address to delete
     * @return the number of addresses deleted (0 if not found, 1 if successfully deleted)
     */
    @Modifying
    Long deleteByAddressId(Integer addressId);
}
