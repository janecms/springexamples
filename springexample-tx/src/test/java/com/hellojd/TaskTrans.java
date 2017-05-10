package com.hellojd;

import com.hellojd.springexample.bean.Blog;
import com.hellojd.springexample.dao.BlogDao;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.Exchanger;
public class TaskTrans implements Runnable {
    Exchanger<Integer> blogIdExchanger;
    @Autowired
    BlogDao blogDao;
    String threadname;
    private long sleeptime;

    public TaskTrans(String threadname, long sleeptime, Exchanger<Integer> blogIdExchanger) {
        this.blogIdExchanger = blogIdExchanger;
        this.threadname = threadname;
        this.sleeptime = sleeptime;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public void run() {
        Blog blog = new Blog();
        blog.setAuthor(threadname);
        blog.setTitle(DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        int blogId = blogDao.save(blog);
        try {
//            blogIdExchanger.exchange(blogId);
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BlogDao getBlogDao() {
        return blogDao;
    }


    public String getThreadname() {
        return threadname;
    }


    public long getSleeptime() {
        return sleeptime;
    }
}
