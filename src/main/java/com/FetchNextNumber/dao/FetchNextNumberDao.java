package com.FetchNextNumber.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FetchNextNumberDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FetchNextNumberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int fetchNumber(String categoryCode) {
        String sql = "SELECT Value FROM Numbers WHERE CategoryCode = ?";
        try {
            int number = jdbcTemplate.queryForObject(sql, Integer.class, categoryCode);
            return number;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public void updateNumber(String categoryCode, int newValue) {
        String sql = "UPDATE Numbers SET Value = ? WHERE CategoryCode = ?";
        jdbcTemplate.update(sql, newValue, categoryCode);
    }


    public int fetchNumberByCategoryCode(String categoryCode) {
        String sql = "SELECT Value FROM Numbers WHERE CategoryCode = ?";
        Integer number = jdbcTemplate.queryForObject(sql, Integer.class, categoryCode);
        return (number != null) ? number : 0;
    }

}
