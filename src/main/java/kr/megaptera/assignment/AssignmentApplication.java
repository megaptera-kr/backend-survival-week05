package kr.megaptera.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AssignmentApplication {

    public static void main(String[] args) {

//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(AssignmentApplication.class);
//
//        GetPostService getPostService =
//                context.getBean("getPostService", GetPostService.class);
//        GetPostsService getPostsService =
//                context.getBean("getPostsService", GetPostsService.class);
//        CreatePostService createPostService =
//                context.getBean("createPostService", CreatePostService.class);
//
//
//        System.out.println("-".repeat(80));
//        System.out.println(getPostService);
//        System.out.println(getPostsService);
//        System.out.println(createPostService);

        SpringApplication.run(AssignmentApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
                        .allowedOrigins("*");
            }
        };
    }

}
