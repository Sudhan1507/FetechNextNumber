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
            // Execute the SQL query to fetch the number based on the categoryCode
            int number = jdbcTemplate.queryForObject(sql, Integer.class, categoryCode);
            return number;
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where no result is found for the given categoryCode
            return 0; // or throw an exception or handle the case as per your requirements
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
