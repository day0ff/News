package com.day0ff.news.service;

import com.day0ff.news.entity.Categories;
import com.day0ff.news.entity.News;
import com.day0ff.news.entity.Persons;
import com.day0ff.news.entity.Tags;
import com.day0ff.news.repository.NewsRepository;
import com.day0ff.news.service.impl.NewsImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NewsImplTest.NewsImplTestContextConfiguration.class})
public class NewsImplTest {
    @Configuration
    static class NewsImplTestContextConfiguration {

        @Bean
        public NewsService newsService() {
            return (NewsService) new NewsImpl();
        }

        @Bean
        public NewsRepository newsRepository() {
            return Mockito.mock(NewsRepository.class);
        }

    }

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsRepository newsRepository;

    private Persons person = new Persons();

    private News news = new News(person, "Заголовок", "Артикаль","Статья","Картинка",new Date(),0,true);
    private List<News> newsList = new ArrayList<>();

    private Tags tag = new Tags("тест");
    private List<Tags> tagsList = new ArrayList<>();

    private Categories category = new Categories("Тесты");
    private List<Categories> categoriesList = new ArrayList<>();

    @Before
    public void setup() {
        person.setId(1L);
        news.setId(1L);
        tag.setId(1L);
        tagsList.add(tag);
        news.setTags(tagsList);
        category.setId(1L);
        categoriesList.add(category);
        newsList.add(news);
        Mockito.when(newsRepository.saveAndFlush(news)).thenReturn(news);
        Mockito.doNothing().when(newsRepository).delete(any(News.class));
        Mockito.when(newsRepository.findById(1L)).thenReturn(news);
        Mockito.when(newsRepository.findAll()).thenReturn(newsList);
        Mockito.when(newsRepository.fetchNewsFindById(1L)).thenReturn(news);
        Mockito.when(newsRepository.fetchNewsCategoriesFindById(1L)).thenReturn(news);
        Mockito.when(newsRepository.fetchNewsFindByPersonId(1L)).thenReturn(newsList);
        Mockito.when(newsRepository.fetchNewsCountFindByPersonId(1L)).thenReturn(1);
        Mockito.when(newsRepository.findNewsByCategories("Тесты")).thenReturn(newsList);
        Mockito.when(newsRepository.fetchNewsFindByTagId(1L)).thenReturn(newsList);
        Mockito.when(newsRepository.getNewsByTagTitle("тест")).thenReturn(newsList);
        Mockito.doNothing().when(newsRepository).incrementNewsViews(1L);
    }

    @Test
    public void saveTest() throws Exception {
        News result = newsService.save(news);
        assertNotNull(result);
        assertSame(result,news);
        assertEquals(result.getId(), news.getId());
    }

    @Test
    public void deleteTest() throws Exception {
        newsService.delete(1L);
    }

    @Test
    public void findByIdTest() throws Exception {
        News result = newsService.findById(1L);
        assertNotNull(result);
        assertSame(result,news);
        assertEquals(result.getId(), news.getId());
    }

    @Test
    public void findAllTest() throws Exception {
        List<News> result = newsService.findAll();
        assertNotNull(result);
        assertSame(result,newsList);
        assertEquals(result.get(0), newsList.get(0));
    }

    @Test
    public void fetchFindByIdTest() throws Exception {
        News result = newsService.fetchFindById(1L);
        assertNotNull(result);
        assertSame(result,news);
        assertEquals(result.getTags(), news.getTags());
    }

    @Test
    public void fetchNewsCategoriesFindByIdTest() throws Exception {
        News result = newsService.fetchNewsCategoriesFindById(1L);
        assertNotNull(result);
        assertSame(result,news);
        assertEquals(result.getCategories(), news.getCategories());
    }

    @Test
    public void fetchNewsFindByPersonIdTest() throws Exception {
        List<News> result = newsService.fetchNewsFindByPersonId(1L);
        assertNotNull(result);
        assertSame(result,newsList);
        assertEquals(result.get(0), newsList.get(0));
    }

    @Test
    public void fetchNewsCountFindByPersonIdTest() throws Exception {
        int result = newsService.fetchNewsCountFindByPersonId(1L);
        assertNotNull(result);
        assertEquals(result,1);
    }

    @Test
    public void findNewsByCategoriesTest() throws Exception {
        List<News> result = newsService.findNewsByCategories("Тесты");
        assertNotNull(result);
        assertSame(result,newsList);
        assertEquals(result.get(0), newsList.get(0));
    }

    @Test
    public void getNewsFindByTagIdTest() throws Exception {
        List<News> result = newsService.getNewsFindByTagId(1L);
        assertNotNull(result);
        assertSame(result,newsList);
        assertEquals(result.get(0), newsList.get(0));
    }

    @Test
    public void getNewsFindByTagTitleTest() throws Exception {
        List<News> result = newsService.getNewsFindByTagTitle("тест");
        assertNotNull(result);
        assertSame(result,newsList);
        assertEquals(result.get(0), newsList.get(0));
    }

    @Test
    public void incrementNewsViewsTest() throws Exception {
        newsService.incrementNewsViews(1L);
    }
}
