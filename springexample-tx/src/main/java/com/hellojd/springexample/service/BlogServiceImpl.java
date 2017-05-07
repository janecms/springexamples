package com.hellojd.springexample.service;

import com.hellojd.springexample.bean.Blog;
import com.hellojd.springexample.bean.Event;
import com.hellojd.springexample.dao.BlogDao;
import com.hellojd.springexample.dao.EventDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/7.
 */
@Service("blogService")
public class BlogServiceImpl implements  BlogService {
    private static final Logger LOGGER = Logger.getLogger(BlogServiceImpl.class);
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private EventDao eventDao;
    @Override
    @Transactional
    public int saveBlog(Blog blog) {
           int blogId=0;
        try {
            blogId = this.blogDao.save(blog);
            Event event = new Event();
            event.setEventDate(new Date());
            String eventStr= MessageFormat.format("this is a event from blog {0}", blogId);
            event.setTitle(eventStr);
            if(blog.getTitle().contains("error")){
                throw new Exception("模拟失败情况，手动触发异常");
            }
            this.eventDao.save(event);
        } finally {
            return blogId;
        }
    }

    @Override
    public Blog get(int blogId) {
        return this.blogDao.get(blogId);
    }
}
