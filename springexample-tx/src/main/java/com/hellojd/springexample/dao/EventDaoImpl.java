package com.hellojd.springexample.dao;

import com.hellojd.springexample.bean.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/5/7.
 */
@Repository
public class EventDaoImpl implements EventDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    final String INSERT_SQL="insert into events(event_date,title)values(?,?)";
    @Override
    public void save(Event event) {
        jdbcTemplate.update(INSERT_SQL,event.getEventDate(),event.getTitle());
    }
}
