package com.kenschool.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
public class ProjectSecurityConfig {

    /*extends WebSecurityConfigurerAdapter*/
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        // Permit All Requests inside the Web Application
        http.authorizeRequests().
                    anyRequest().permitAll().
                    and().formLogin()
                    .and().httpBasic();
        // Deny All Requests inside the Web Application
        *//*http.authorizeRequests().
                anyRequest().denyAll().
                and().formLogin()
                .and().httpBasic();*//*
    }*/

    /*
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
// At first we where using the WebSecurityConfigurerAdapter

    /* we can do the Bean of SecurityFilterChain in the below pattern also just for knowledge(Below code)
    * @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                    .loginPage("/login")
                    .permitAll()
                )
                .logout((logout) -> logout.permitAll());

            return http.build();
        }*/
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Permit All Requests inside the Web Application
       /* http.authorizeRequests().anyRequest().permitAll().
                and().formLogin()
                .and().httpBasic(); */ // This Can be do or below code is using the lambda expression


//         Deny All Requests inside the Web Application it first take the login credentials then will show access denied
            /*http.authorizeRequests().anyRequest().denyAll().
                    and().formLogin()
                    .and().httpBasic();*/


        // This is the code for permit all requests
       /* http.authorizeRequests((requests)->{
            try {
                requests.anyRequest().permitAll().and().formLogin().and().httpBasic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        http.formLogin();
        http.httpBasic(); */

        // Now we are going to customize the http request with out needs using different methods
        // We can use regexMatches,antMatches for checking the request url
        // These are nested conditions if first one is false automatically second one will be triggered
        // Since /holidays req contains a uri path variable we need to add /** followed by the request
        // We can give authenticated() in palace of permitAll() for any of the matches so that a req to that API page will show login by spring Security
        // This csrf disable is not required if we are using the th:action , required if we are using the normal action like in react or something
        http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/public/register").and().
                authorizeRequests().mvcMatchers("/home").permitAll()
                .mvcMatchers("/displayProfile").authenticated()
                .mvcMatchers("/updateProfile").authenticated()
                .mvcMatchers("/EditProfile").authenticated()
                .mvcMatchers("/displayMessages").hasRole("ADMIN").
                mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/holidays/**").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/saveMsg").permitAll()
                .mvcMatchers("/courses").permitAll()
                .mvcMatchers("/about").permitAll()
                .mvcMatchers("/public/**").permitAll()
                .mvcMatchers("/login").permitAll()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true").permitAll().
                and().logout().logoutSuccessUrl("/login?logout=true").
                invalidateHttpSession(true).permitAll().


                and().httpBasic();
//        http.headers().frameOptions().disable(); //relate to H2 db
//        ignoringAntMatchers("/h2-console/**").and().antMatchers("/h2-console/**").permitAll() both line 92 and 93 related to H2
        return http.build();

    }

    // Now we are using the db to validate the username and psswd we don't need this inMemory storing mechanism
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        // In this code the withDefaultPasswordEncoder() is deprecated so fixed with another method that is person2......
//        UserDetails admin1= User.withDefaultPasswordEncoder().username("admin").
//                password("admin@123").roles("ADMIN").build();
//        UserDetails person1=User.builder().username("niko").password(encoder.encode("Nikhil@1998")).roles("USER")
//                .build();
//        UserDetails person2=User.builder().username("user").
//                password(encoder.encode("user@123")).roles("USER").build();
//        // This encoding of the password is with the modern technique like BCryptPasswordEncoder
//        UserDetails person3=User.builder().username("Balubabu").password(encoder2().encode("Balu@1998"))
//                .roles("Admin").build();
//        return new InMemoryUserDetailsManager(admin1,person2,person1);
//    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
