package com.hellojd.springexample.dao;

import com.hellojd.springexample.bean.Blog;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface BlogDao {
    public int save(Blog blog);
    public Blog get(int blogId);
}
