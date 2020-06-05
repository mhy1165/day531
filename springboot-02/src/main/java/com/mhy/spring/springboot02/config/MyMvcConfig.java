package com.mhy.spring.springboot02.config;

import com.mhy.spring.springboot02.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurer可以来扩展SpringMVC的功能
//@EnableWebMvc //全面接管springmvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //添加视图解析器
        // super.addViewControllers(registry);
        //浏览器发送 /mhy 请求来到 success
       // registry.addViewController("/mhy").setViewName("success");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    //@Bean //将组件注册在容器   springboot2.0不加也没事
 @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer configurer = new WebMvcConfigurer() {

            private final String[] excludePaths = {"/","/index","/index.html","/user/login","/asserts/**","/webjars/**"};
            //注册拦截器
            /*
                addPathPatterns：拦截的请求
                在spring2.0+的版本中，只要用户自定义了拦截器，则静态资源会被拦截。但是在spring1.0+的版本中，是不会拦截静态资源的。
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {

                registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns("/","index","/index.html","/user/login","/asserts/**","/webjars/**");

            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/crud").setViewName("login");


            }
        };
        return configurer;
    }



}
