package com.hellojd.springexample.dao;

import com.hellojd.springexample.bean.Blog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/5/7.
 */
@Repository
public class BlogDaoImpl implements BlogDao {
    private static final Logger LOGGER = Logger.getLogger(BlogDaoImpl.class);
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
        Blog blog = null;
        try {
            blog = this.jdbcTemplate.queryForObject(GET_BLOG_BY_ID, new RowMapper<Blog>() {
                @Override
                public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Blog blog = new Blog();
                    blog.setAuthor(rs.getString("author"));
                    blog.setTitle(rs.getString("title"));
                    blog.setId(rs.getInt("id"));
                    return blog;
                }
            }, blogId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("没有找到记录",e);
        }
        return blog;
    }
}
