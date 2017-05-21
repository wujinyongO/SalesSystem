package org.vege.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rustbell on 5/21/17.
 */
public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet r, int i) throws SQLException {
        return new Order(
                r.getLong("id"),
                r.getLong("foodId"),
                r.getLong("userId"),
                r.getLong("foodNumber"),
                r.getLong("sellerId"),
                r.getTimestamp("create_date"),
                r.getString("getFoodTime"),
                r.getLong("isFinish"),
                r.getString("foodCode")
        );
    }
}
