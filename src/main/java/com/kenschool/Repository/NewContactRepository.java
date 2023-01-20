package com.kenschool.Repository;

import com.kenschool.Model_POJOs.NewPOJOContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewContactRepository extends JpaRepository<NewPOJOContactEntity, Integer> {
    List<NewPOJOContactEntity> findByStatus(String open);
}
