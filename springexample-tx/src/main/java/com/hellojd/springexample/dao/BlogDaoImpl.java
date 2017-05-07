package com.hellojd.springexample.dao;

import com.hellojd.springexample.bean.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/5/7.
 */
@Repository
public class BlogDaoImpl implements BlogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int save(final Blog blog) {
        final String INSERT_SQL="insert into blog(title,author)values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(INSERT_SQL, new String[] {"id"});//key
                        ps.setString(1, blog.getTitle());
                        ps.setString(2,blog.getAuthor());
                        return ps;
                    }
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Blog get(int blogId) {
        final String GET_BLOG_BY_ID="select id,title,author from blog where id=?";
        return this.jdbcTemplate.queryForObject(GET_BLOG_BY_ID,Blog.class,blogId);
    }
}
