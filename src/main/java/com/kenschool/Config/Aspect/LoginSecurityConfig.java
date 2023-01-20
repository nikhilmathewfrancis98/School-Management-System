//package com.kenschool.Config.Aspect;
//
//
//import com.kenschool.Security.UsernamePsswdLoginAuthentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//// If we are not using the Spring Boot app we need this bean creation and pass the UsernamePsswdLoginAuthentication class
//// instance here to validate the login
//// For the login authentication we are going to add the Bean for WebconfigureAdaptor which is depricated same can
//// be done in ProjectConfig class so here creating the bean here this code can be done inside the ProjectConfig class
//
// Since we don't need this @EnableWebSecurity for @Configuration classes oz it's already integrated with the annotation this can show
// some Positive matches notifications like:
//        SpringBootWebSecurityConfiguration.WebSecurityEnablerConfiguration matched:
//                - @ConditionalOnClass found required class 'org.springframework.security.config.annotation.web.configuration.EnableWebSecurity' (OnClassCondition)
//        - @ConditionalOnMissingBean (names: springSecurityFilterChain; SearchStrategy: all) did not find any beans (OnBeanCondition)

//@Configuration
//@EnableWebSecurity
//@ComponentScan("com.kenschool.Security")
//public class LoginSecurityConfig {
//
//    @Autowired
//    private UsernamePsswdLoginAuthentication authProvider;
//
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authProvider);
//        return authenticationManagerBuilder.build();
//    }
//
//
//
//}