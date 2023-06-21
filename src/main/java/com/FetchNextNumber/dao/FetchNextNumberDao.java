package com.FetchNextNumber.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class FetchNextNumberDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FetchNextNumberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int fetchNumberByCategoryCode(String categoryCode) {
        String sql = "SELECT Value FROM Numbers WHERE CategoryCode = ?";
        Integer number = jdbcTemplate.queryForObject(sql, Integer.class, categoryCode);
        return (number != null) ? number : 0;
    }
}
