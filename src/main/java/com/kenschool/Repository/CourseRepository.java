package com.kenschool.Repository;

import com.kenschool.Model_POJOs.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// Since the JpaRepository internally extends the PagingAndSortingRepository interface no need to extends it anymore
@Repository
public interface CourseRepository extends JpaRepository<Courses,Integer> {

// These are named query strings
List<Courses> findByOrderByNameDesc();
List<Courses> findByOrderByName();


}
