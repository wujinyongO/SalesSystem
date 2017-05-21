package org.vege.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vege.model.VegeMapper;
import org.vege.model.Vege;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rustbell on 5/20/17.
 */
@Repository
public class VegeDao {
    @Resource
    JdbcTemplate jdbcTemplate;

    public Vege getByID(Long id)
    {
        String[] arg = new String[1];
        arg[0] = id.toString();
        Vege v = jdbcTemplate.query("select * from vegetable where id=?", arg, new VegeMapper()).get(0);
        return v;
    }

    public List<Vege> getBySellerId(Long sellerId)
    {
        String[] arg = new String[1];
        arg[0] = sellerId.toString();
        return jdbcTemplate.query("select * from vegetable where sellerId=?", arg, new VegeMapper());
    }

    public List<Vege> getAll()
    {
        return jdbcTemplate.query("call show_vegetable()", new VegeMapper());
    }

    public int add(Vege vege)
    {
        return jdbcTemplate.update("call add_vegetable(?,?,?,?,?)",
                vege.getFoodimg(),
                vege.getFoodprice(),
                vege.getFoodname(),
                vege.getPricetype(),
                vege.getSellerid());
    }

    public int remove(Long id)
    {
        return jdbcTemplate.update("delete from vegetable where id=?", id);
    }

    public int modify(Vege vege, Long vid)
    {
        return jdbcTemplate.update("update vegetable set foodImg=?,foodPrice=?,foodName=?,priceType=? where id=?",
                vege.getFoodimg(),
                vege.getFoodprice(),
                vege.getFoodname(),
                vege.getPricetype(),
                vid);
    }
}
