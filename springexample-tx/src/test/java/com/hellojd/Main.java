package com.hellojd;

import com.hellojd.springexample.bean.Blog;
import com.hellojd.springexample.service.BlogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2017/5/7.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        BlogService blogService =(BlogService)ctx.getBean("blogService");
        Blog blog =new Blog();
        blog.setTitle("error Blog NULL by declare");
        blog.setAuthor("破坏者");//超过长度
        int blogId = blogService.saveBlog(blog);
        System.out.println("blogId=="+blogId);
        Blog blogPO = blogService.get(blogId);
        System.out.println("blogPO="+blogPO);
    }
}
