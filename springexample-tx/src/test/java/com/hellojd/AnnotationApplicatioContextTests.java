package com.hellojd;
import static org.junit.Assert.*;
import com.hellojd.springexample.bean.Blog;
import com.hellojd.springexample.bean.Event;
import com.hellojd.springexample.dao.BlogDao;
import com.hellojd.springexample.dao.EventDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/annotationApplicationContext.xml")
public class AnnotationApplicatioContextTests
{
    @Resource
    BlogDao blogDao;
    @Resource
    EventDao eventDao;

    @Test
    public void testSaveBlog(){
        Blog blog = new Blog();
        blog.setTitle("拿破仑");
        blog.setAuthor("Bruse Lee");
        int blogId = this.blogDao.save(blog);
        assertTrue(blogId>0);
    }
    @Test
    public void testGetBlog(){
        Blog blog = this.blogDao.get(112);
        assertEquals(blog.getTitle(), "拿破仑");
    }

    @Test
    public void testSaveEvent(){
        Event event = new Event();
        event.setTitle("this is a blog insert event");
        event.setEventDate(new Date());
        this.eventDao.save(event);
    }
}
