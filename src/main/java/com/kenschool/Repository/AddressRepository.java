package com.kenschool.Repository;

import com.kenschool.Model_POJOs.AddressPoJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressPoJO, Integer> {
}
