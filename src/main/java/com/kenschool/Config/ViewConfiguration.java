// This java class is for configuring the views with static contents and not to make  the Controller class
package com.kenschool.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/courses").setViewName("courses.html");
        registry.addViewController("/About").setViewName("about.html");
//        registry.addViewController("/dashboard").setViewName("dashboard.html");
    }
}
