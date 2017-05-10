package com.hellojd;

import com.hellojd.springexample.bean.Blog;
import com.hellojd.springexample.dao.BlogDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Exchanger;

/**
 * Created by Administrator on 2017/5/8.
 */
public class TaskQueryTrans implements Runnable{
    private static Logger LOGGER = Logger.getLogger(TaskQueryTrans.class);
    @Autowired
    BlogDao blogDao;
    Exchanger<Integer> blogIdExchanger = null;
    String threadname;
    public TaskQueryTrans(String threadname,Exchanger<Integer> blogIdExchanger) {
        this.threadname = threadname;
        this.blogIdExchanger=blogIdExchanger;
    }
    public void run() {
        try {
            Integer exchangeBlogId = blogIdExchanger.exchange(-1);
            System.out.println("##########################"+exchangeBlogId);
            Blog blogPO = blogDao.get(exchangeBlogId);
            LOGGER.info("blogPO=="+blogPO);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
