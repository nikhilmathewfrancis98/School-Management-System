//package com.kenschool.Repository;
//
//import com.kenschool.Model_POJOs.POJOContact;
//import com.kenschool.Rowmappers.ContactRowMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Repository
//public class ContactRepository {
//    private JdbcTemplate jdbcTemplate;
//
//    // Done the beplow code coz we need to create the Bean of the DataSource
////    @Autowired
////    public void ContactRepository(DataSource dataSource){
////        jdbcTemplate=new JdbcTemplate(dataSource);
////    }
//// If we are not creating the Bean of DataSource we only need to Autowire jdbcTemplate
//    @Autowired
//    public ContactRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int SaveContactMsg(POJOContact pojoContact){
//        String sql="Insert into contact_msg (name,mobile_num,email,subject,message,status,created_at,created_by) values" +
//                "(?,?,?,?,?,?,?,?)";
//      return this.jdbcTemplate.update(sql,pojoContact.getName(),pojoContact.getMobileNum(),
//                pojoContact.getEmail(),pojoContact.getSubject(),pojoContact.getMessage(),pojoContact.getStatus(),pojoContact.getCreatedAt()
//      ,pojoContact.getCreatedBy());
//    }
//
//    public List<POJOContact> findMsgsWithStatus(String open) {
//        String sql="Select * from contact_msg where status=?";
//        return jdbcTemplate.query(sql,new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, open);
//            }
//        },new ContactRowMapper());
//    }
//
//    public int setstatusUpdate(int contactId, String name, String close) {
//        String Sql="Update contact_msg set updated_at=?,updated_by=?,status=? where contact_id=?";
//        //Lambda expression of the below code
////        ps -> {
////            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
////            ps.setString(2,name);
////            ps.setString(3,close);
////            ps.setInt(4,contactId);
////        });
//        return jdbcTemplate.update(Sql, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
//                ps.setString(2,name);
//                ps.setString(3,close);
//                ps.setInt(4,contactId);
//            }
//        });
//    }
//}
