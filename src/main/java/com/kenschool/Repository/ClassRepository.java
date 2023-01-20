package com.kenschool.Repository;

import com.kenschool.Model_POJOs.ClassPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassPojo, Integer> {
}
