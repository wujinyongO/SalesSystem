package org.vege.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by rustbell on 5/20/17.
 */
public class SellerMapper implements RowMapper<Seller> {
    @Override
    public Seller mapRow(ResultSet r, int i) throws SQLException {
        return new Seller(
                r.getLong("id"),
                r.getString("phoneNumber"),
                r.getString("windowImg"),
                r.getString("password"),
                r.getLong("state"),
                r.getString("sellerName"),
                r.getString("windowName")
        );
    }
}
