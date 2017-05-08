package com.hellojd.springexample.service;

import com.hellojd.springexample.bean.Blog;
import com.hellojd.springexample.bean.Event;
import com.hellojd.springexample.dao.BlogDao;
import com.hellojd.springexample.dao.EventDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/7.
 * 编程式事务
 */
public class BlogProgramServiceImpl implements BlogService {
    private static final Logger LOGGER = Logger.getLogger(BlogProgramServiceImpl.class);
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Override
    public int saveBlog(Blog blog) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        int blogId=0;
        try{
            blogId = this.blogDao.save(blog);
            Event event = new Event();
            event.setEventDate(new Date());
            String eventStr= MessageFormat.format("this is a event from blog {0}",blogId);
            event.setTitle(eventStr);
            if(blog.getTitle().contains("error")){
                throw new RuntimeException("模拟失败情况，手动触发异常");
            }
            this.eventDao.save(event);
            transactionManager.commit(status);
        }catch (Exception e){
            LOGGER.error("保存文章失败",e);
            transactionManager.rollback(status);
        }
        return blogId;
    }

    @Override
    public Blog get(int blogId) {
        return this.blogDao.get(blogId);
    }
}
