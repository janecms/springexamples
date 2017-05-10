package com.hellojd;

import com.hellojd.springexample.bean.Blog;
import com.hellojd.springexample.dao.BlogDao;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.Exchanger;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-test.xml")
public class IsolationServiceTest {
    private static Logger LOGGER = Logger.getLogger(IsolationServiceTest.class);
    @Resource
    Runnable taskTrans;
    @Resource
    Runnable taskQueryTrans;
    @Test
    public void testIsolationUNCOMMITTED()
    {
        taskTrans.run();
//        new Thread(taskTrans).start();
//        new Thread(taskQueryTrans).start();
    }
    public void isolationCOMMITTED(){

    }
    public void isolationREPEATABLE(){

    }
    public void isolationSERIALIZABLE(){

    }
}
