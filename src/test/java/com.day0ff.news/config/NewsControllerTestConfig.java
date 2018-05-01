package com.day0ff.news.config;

import com.day0ff.news.controller.NewsController;
import com.day0ff.news.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;

import static org.mockito.Mockito.mock;

@EnableWebMvc
@Configuration
public class NewsControllerTestConfig {

    @Bean
    public MessageSource messageSource() {
        return mock(MessageSource.class);
    }

    @Bean
    public NewsController newsController() {
        return new NewsController();
    }

    @Bean
    public NewsService newsService() {
        return mock(NewsService.class);
    }

    @Bean
    public PersonsService personsService()  {
        return mock(PersonsService.class);
    }

    @Bean
    public CommentsService commentsService()  {
        return mock(CommentsService.class);
    }

    @Bean
    public LikesService likesService()  {
        return mock(LikesService.class);
    }

    @Bean
    public CategoriesService categoriesService()  {
        return mock(CategoriesService.class);
    }

    @Bean
    public TagsService tagsService()  {
        return mock(TagsService.class);
    }

    @Bean
    public RolesService rolesService() {
        return mock(RolesService.class);
    }

    @Bean
    public UsersService usersService() {
        return mock(UsersService.class);
    }

    @Bean
    public MediaType application_JSON_UTF8() {
        return new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8")
        );
    }
}
