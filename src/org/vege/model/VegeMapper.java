package org.vege.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rustbell on 5/20/17.
 */
public class VegeMapper implements RowMapper<Vege>
{
    @Override
    public Vege mapRow(ResultSet r, int i) throws SQLException {
        return new Vege(
                r.getLong("id"),
                r.getString("foodImg"),
                r.getDouble("foodPrice"),
                r.getString("foodName"),
                r.getString("priceType"),
                r.getLong("sellerId")
        );
    }
}
