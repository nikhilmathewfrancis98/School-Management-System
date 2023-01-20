//package com.kenschool.Rowmappers;
//
//import com.kenschool.Model_POJOs.POJOHoliday;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class HolidayRowMapper implements RowMapper<POJOHoliday> {
//    @Override
//    public POJOHoliday mapRow(ResultSet rs, int rowNum) throws SQLException {
//        POJOHoliday holiday=new POJOHoliday();
//        holiday.setDay(rs.getString("holiday"));
//        holiday.setReason(rs.getString("reason"));
//        holiday.setType(rs.getString("type"));
//        return holiday;
//    }
//}
