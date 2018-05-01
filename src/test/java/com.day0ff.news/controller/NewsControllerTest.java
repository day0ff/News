package com.day0ff.news.controller;

import com.day0ff.news.config.NewsControllerTestConfig;
import com.day0ff.news.entity.*;
import com.day0ff.news.repository.NewsRepository;
import com.day0ff.news.service.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NewsControllerTestConfig.class})
@WebAppConfiguration
public class NewsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private MediaType application_JSON_UTF8;

    @Autowired
    private NewsService newsService;

    @Autowired
    private PersonsService personsService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private NewsController newsController;

    @Autowired
    private MessageSource messageSource;

    private Users user = new Users("ivan", "1234", true);
    private List<Users> usersList = new ArrayList<>();

    private Users petr = new Users("petr", "1234", true);

    private Roles role = new Roles("USER");
    private List<Roles> rolesList = new ArrayList<>();

    private Persons person = new Persons(user, "Иван", "Иванов", "ivanushka", "image");
    private List<Persons> personsList = new ArrayList<>();

    private News news = new News(person, "Заголовок", "Артикаль", "Статья", "Картинка", new Date(), 0, true);
    private List<News> newsList = new ArrayList<>();

    private Comments comment = new Comments(person, news, "Коммкнтарий");
    private List<Comments> commentsList = new ArrayList<>();

    private Tags tag = new Tags("тест");
    private List<Tags> tagsList = new ArrayList<>();

    private Categories category = new Categories("Тесты");
    private List<Categories> categoriesList = new ArrayList<>();

    private Likes like = new Likes(news, person);
    private List<Likes> likesList = new ArrayList<>();


    private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
        user.setId(1L);
        role.setId(1L);
        rolesList.add(role);
        user.setRoles(rolesList);
        petr.setId(1L);
        petr.setRoles(rolesList);
        person.setUser(user);
        news.setId(1L);
        tag.setId(1L);
        tagsList.add(tag);
        news.setTags(tagsList);
        category.setId(1L);
        categoriesList.add(category);
        news.setCategories(categoriesList);
        newsList.add(news);
        comment.setId(1L);
        commentsList.add(comment);
        person.setComments(commentsList);
        news.setComments(commentsList);
        like.setId(1L);
        likesList.add(like);
        Mockito.when(categoriesService.findAll()).thenReturn(categoriesList);
        Mockito.when(likesService.findAll()).thenReturn(likesList);
        Mockito.when(rolesService.findAll()).thenReturn(rolesList);
        Mockito.when(likesService.getCountNewsLikes(1L)).thenReturn(1);
        Mockito.when(likesService.getCountPersonLikes(1L)).thenReturn(1);
        Mockito.when(likesService.isPersonLikeNews(1L, 1L)).thenReturn(1);
        Mockito.when(likesService.findByNewsAndPerson(1L, 1L)).thenReturn(like);
        Mockito.when(newsService.findAll()).thenReturn(newsList);
        Mockito.when(newsService.findById(1L)).thenReturn(news);
        Mockito.when(tagsService.findAll()).thenReturn(tagsList);
        Mockito.when(newsService.fetchFindById(1L)).thenReturn(news);
        Mockito.when(newsService.fetchNewsCategoriesFindById(1L)).thenReturn(news);
        Mockito.when(newsService.findNewsByCategories("Test")).thenReturn(newsList);


        Mockito.when(commentsService.getCountNewsComments(1L)).thenReturn(1);
        Mockito.when(newsService.fetchNewsCountFindByPersonId(1L)).thenReturn(1);


        Mockito.when(commentsService.getCountPersonComments(1L)).thenReturn(1);
        Mockito.doNothing().when(newsService).incrementNewsViews(1L);
        Mockito.when(usersService.getCountUserByUserName("ivan")).thenReturn(1);
        Mockito.when(usersService.getCountUserByUserName("petr")).thenReturn(0);
        Mockito.when(usersService.save(user)).thenReturn(user);
        Mockito.when(usersService.save(petr)).thenReturn(petr);
        Mockito.when(rolesService.findById(1L)).thenReturn(role);
        Mockito.when(usersService.findByNameAndPassword("ivan", "1234")).thenReturn(user);
        Mockito.when(personsService.save(person)).thenReturn(person);
    }

    @Test
    public void getCategoriesTest() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].category", is("Тесты")));
        Mockito.verify(categoriesService, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(categoriesService);
    }

    @Test
    public void getLikesTest() throws Exception {
        mockMvc.perform(get("/likes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].news.title", is("Заголовок")))
                .andExpect(jsonPath("$[0].person.firstName", is("Иван")));
        Mockito.verify(likesService, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(likesService);
    }

    @Test
    public void getRolesTest() throws Exception {
        mockMvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].role", is("USER")));
        Mockito.verify(rolesService, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(rolesService);
    }

    @Test
    public void getLikesCountNewsTest() throws Exception {
        mockMvc.perform(get("/news/likes/count/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", is(1)));
        Mockito.verify(likesService, times(1)).getCountNewsLikes(1L);
        Mockito.verifyNoMoreInteractions(likesService);
    }

    @Test
    public void getLikesCountPersonTest() throws Exception {
        mockMvc.perform(get("/likes/count/person/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", is(1)));
        Mockito.verify(likesService, times(1)).getCountPersonLikes(1L);
        Mockito.verifyNoMoreInteractions(likesService);
    }

    @Test
    public void isPersonLikeNewsTest() throws Exception {
        mockMvc.perform(get("/likes/{newsId}/{personId}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", is(1)));
        Mockito.verify(likesService, times(1)).isPersonLikeNews(1L, 1L);
        Mockito.verifyNoMoreInteractions(likesService);
    }

/*
    @Test
    public void getLikeByNewsAndPersonTest() throws Exception {
        mockMvc.perform(get("/like/{newsId}/{personId}", 1L, 1L))
                .andExpect(status().isOk());
        Mockito.verify(likesService, times(1)).findByNewsAndPerson(1L, 1L);
        Mockito.verifyNoMoreInteractions(likesService);
    }

    @Test
    public void getNewsTest() throws Exception {
        mockMvc.perform(get("/news"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].news.title", is("Заголовок")));
        Mockito.verify(newsService, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(newsService);
    }

    @Test
    public void getNewsByIdTest() throws Exception {
        mockMvc.perform(get("/news/{newsId}", 1L))
                .andExpect(status().isOk());
        Mockito.verify(newsService, times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(newsService);
    }
*/

    @Test
    public void getTagsTest() throws Exception {
        mockMvc.perform(get("/tags"))
                .andExpect(status().isOk()).andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].tag", is("тест")));
        Mockito.verify(tagsService, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(tagsService);
    }

    @Test
    public void getTagsByNewsIdTest() throws Exception {
        mockMvc.perform(get("/tags/news/{newsId}", 1L))
                .andExpect(status().isOk()).andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].tag", is("тест")));
        Mockito.verify(newsService, times(1)).fetchFindById(1L);
        Mockito.verifyNoMoreInteractions(newsService);
    }

    @Test
    public void getNewsCategoryByIdTest() throws Exception {
        mockMvc.perform(get("/category/news/{id}", 1L))
                .andExpect(status().isOk()).andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].category", is("Тесты")));
        Mockito.verify(newsService, times(1)).fetchNewsCategoriesFindById(1L);
        Mockito.verifyNoMoreInteractions(newsService);
    }
/*
    @Test
    public void getNewsCommentsCountTest() throws Exception {
        mockMvc.perform(get("/news/comments/count/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", is(1)));
        Mockito.verify(commentsService, times(1)).getCountNewsComments(1L);
        Mockito.verifyNoMoreInteractions(commentsService);
    }


    @Test
    public void getNewsByCategoryTest() throws Exception {
        mockMvc.perform(get("/news/category/{category}", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].news.title", is("Заголовок")));
        Mockito.verify(newsService, times(1)).findNewsByCategories("Тесты");
        Mockito.verifyNoMoreInteractions(newsService);
    }
*/

    @Test
    public void getNewsPersonCountTest() throws Exception {
        mockMvc.perform(get("/news/person/count/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", is(1)));
        Mockito.verify(newsService, times(1)).fetchNewsCountFindByPersonId(1L);
        Mockito.verifyNoMoreInteractions(newsService);
    }

    @Test
    public void getCommentsPersonCountTest() throws Exception {
        mockMvc.perform(get("/comments/person/count/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", is(1)));
        Mockito.verify(commentsService, times(1)).getCountPersonComments(1L);
        Mockito.verifyNoMoreInteractions(commentsService);
    }

    @Test
    public void getCommentsNewsCountTest() throws Exception {
        mockMvc.perform(get("/comments/news/count/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(application_JSON_UTF8))
                .andExpect(jsonPath("$", is(1)));
        Mockito.verify(commentsService, times(1)).getCountNewsComments(1L);
        Mockito.verifyNoMoreInteractions(commentsService);
    }

    @Test
    public void incrementNewsViewsTest() throws Exception {
        mockMvc.perform(get("/news/views/{newsId}", 1L))
                .andExpect(status().isOk());
        Mockito.verify(newsService, times(1)).incrementNewsViews(1L);
        Mockito.verifyNoMoreInteractions(newsService);
    }

    @Test
    public void saveUserTest() throws Exception {
        mockMvc.perform(post("/users/save")
                .param("userName", "ivan")
                .param("password", "1234")
        )
                .andExpect(status().isFound());
        Mockito.verify(usersService, times(1)).getCountUserByUserName("ivan");
        Mockito.verify(rolesService, times(0)).findById(1L);
        Mockito.verify(usersService, times(0)).save(any());
        mockMvc.perform(post("/users/save")
                .param("userName", "petr")
                .param("password", "1234")
        )
                .andExpect(status().isOk());
        Mockito.verify(usersService, times(1)).getCountUserByUserName("petr");
        Mockito.verify(rolesService, times(1)).findById(1L);
        Mockito.verify(usersService, times(2)).save(any());
    }

    @Test
    public void saveUserPersonTest() throws Exception {
        mockMvc.perform(post("/users/save/person")
                .param("userName", "ivan")
                .param("password", "1234")
                .param("firstName", "Иван")
                .param("lastName", "Иванов")
                .param("screenName", "ivanushka")
                .param("image", "image")
        )
                .andExpect(status().isOk());
        Mockito.verify(usersService, times(1)).findByNameAndPassword("ivan", "1234");
        Mockito.verify(personsService, times(1)).save(any());
        Mockito.verifyNoMoreInteractions(personsService);
    }
}