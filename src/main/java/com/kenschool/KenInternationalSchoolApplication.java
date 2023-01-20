package com.kenschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.kenschool.Services,com.kenschool.Controller,com.kenschool.Auditing,com.kenschool.Config"})
@EnableJpaRepositories("com.kenschool.Repository")
@EntityScan("com.kenschool.Model_POJOs")
@EnableJpaAuditing(auditorAwareRef = "AuditImplClass")
public class KenInternationalSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(KenInternationalSchoolApplication.class, args);
    }

    // Since we can create another another class for Configuring this bean (look commented SprigDBConfig.java under Config pkg)
    // This works without app.properties and added the dependency spring data jpa
//	@Primary
//	@Bean
//	public DataSource myDataSource(){
//		DataSourceBuilder dataSource = DataSourceBuilder.create();
//		dataSource.driverClassName("org.h2.Driver");
//		dataSource.url("jdbc:h2:mem:testdb");
//		dataSource.username("user");
//		dataSource.password("password");
//		return dataSource.build();
//	}


}
