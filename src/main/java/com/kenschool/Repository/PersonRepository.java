package com.kenschool.Repository;

import com.kenschool.Model_POJOs.PersonPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonPojo, Integer> {
    PersonPojo getByemail(String email);
}
