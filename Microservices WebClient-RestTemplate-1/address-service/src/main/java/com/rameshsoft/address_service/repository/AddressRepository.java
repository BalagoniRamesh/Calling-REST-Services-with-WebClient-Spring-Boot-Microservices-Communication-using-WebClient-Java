package com.rameshsoft.address_service.repository;

import com.rameshsoft.address_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    Optional<Address> findByEmpId(int employeeId);
}
