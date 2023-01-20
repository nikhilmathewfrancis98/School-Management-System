//package com.kenschool.Repository;
//
//import com.kenschool.Model_POJOs.POJOHoliday;
//import com.kenschool.Rowmappers.HolidayRowMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class HolidayRepository {
//    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    public HolidayRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}
//    public List<POJOHoliday> getHolidays() {
////        Actually we don't need a service layer ,Row_mapper for the Holiday because our Holiday fields name and Schema entity names are same so we only need this BeanRowMapper
////        String sql = "SELECT * FROM HOLIDAYS";
////        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
////        return jdbcTemplate.query(sql, rowMapper);
//        String sql="Select * from holidays";
//        return this.jdbcTemplate.query(sql,new HolidayRowMapper());
//    }
//}
