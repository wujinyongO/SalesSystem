package org.vege.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rustbell on 5/21/17.
 */
public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet r, int i) throws SQLException {
        return new Customer(
                r.getLong("id"),
                r.getString("name"),
                r.getString("password"),
                r.getString("phonenumber"),
                r.getString("imageurl"),
                r.getString("address"),
                r.getLong("visit_made"),
                r.getTimestamp("last_visit_time")
        );
    }
}
