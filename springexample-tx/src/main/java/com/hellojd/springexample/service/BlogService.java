package com.hellojd.springexample.service;

import com.hellojd.springexample.bean.Blog;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface BlogService {
    public int saveBlog(Blog blog);
    public Blog get(int blogId);
}
