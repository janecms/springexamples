package com.hellojd;
import static org.junit.Assert.*;
import com.hellojd.springexample.bean.Blog;
import com.hellojd.springexample.bean.Event;
import com.hellojd.springexample.dao.BlogDao;
import com.hellojd.springexample.dao.EventDao;
import com.hellojd.springexample.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class AnnotationApplicatioContextTests
{
    @Resource
    BlogDao blogDao;
    @Resource
    EventDao eventDao;
    @Resource
    BlogService blogProgramService;
    @Resource
    BlogService blogService;
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

    @Test
    public void testSaveBlogOnSuccess(){
        Blog blog =new Blog();
        blog.setAuthor("successAuthor");
        blog.setTitle("事务成功");
        int blogId = blogProgramService.saveBlog(blog);
        Blog blogPO = this.blogDao.get(blogId);
        assertNotNull(blogPO);
    }
    @Test
    public void testSaveBlogOnFailure(){
        Blog blog =new Blog();
        blog.setTitle("error Blog NULL");
        blog.setAuthor("破坏者");//超过长度
        int blogId = blogProgramService.saveBlog(blog);
        assertTrue(blogId>0);
        Blog blogPO = this.blogDao.get(blogId);
        assertNull(blogPO);
    }
    @Test
    public void testSaveBlogOnSuccess2(){
        Blog blog =new Blog();
        blog.setAuthor("successAuthor");
        blog.setTitle("事务成功 by declare");
        int blogId = blogService.saveBlog(blog);
        Blog blogPO = this.blogService.get(blogId);
        assertNotNull(blogPO);
    }

    @Test
    public void testSaveBlogOnFailure2(){
        Blog blog =new Blog();
        blog.setTitle("error Blog NULL by declare");
        blog.setAuthor("破坏者");//超过长度
        int blogId = blogService.saveBlog(blog);
        assertTrue(blogId>0);
        Blog blogPO = this.blogService.get(blogId);
        assertNull(blogPO);
    }
}
