//package com.kenschool.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@Primary
//public class SpringDBConfig {
//    @Bean
//    public DataSource myDataSource(){
//        DriverManagerDataSource dataSource =new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:testdb");
//        dataSource.setUsername("user");
//        dataSource.setPassword("password");
//        return dataSource;
//    }
//}
