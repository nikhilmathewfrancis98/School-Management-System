package com.kenschool.Repository;

import com.kenschool.Model_POJOs.NewPoJoHolidayEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewHolidayRepository extends CrudRepository<NewPoJoHolidayEntity, String> {
    //We can create our own custom Logic method if we need to do some operations other than the CrudRepository provides
}
