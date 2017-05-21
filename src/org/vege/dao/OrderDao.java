package org.vege.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vege.model.Order;
import org.vege.model.OrderMapper;
import org.vege.model.Vegetableorder;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rustbell on 5/21/17.
 */
@Repository
public class OrderDao {

    @Resource
    JdbcTemplate jdbcTemplate;

    public List<Order> getBySellerID(Long id)
    {
        String[] arg = new String[1];
        arg[0] = id.toString();
        List<Order> query = jdbcTemplate.query("select * from vegetableorder where sellerId=?", arg, new OrderMapper());
        return query;
    }

    public List<Order> getByCustomerId(Long id)
    {
        String[] arg = new String[1];
        arg[0] = id.toString();
        List<Order> query = jdbcTemplate.query("select * from vegetableorder where userId=?", arg, new OrderMapper());
        return query;
    }

    public int add(Order o)
    {
        //r(foodId,userId,foodNumber,sellerId,create_date,getFoodTime,isFinish,foodCode)
        return jdbcTemplate.update("call add_order(?,?,?,?,?,?,?)",
                o.getFoodid(),
                o.getUserid(),
                o.getFoodnumber(),
                o.getSellerid(),
                o.getCreate_date(),
                o.getGetfoodtime(),
                o.getIsfinish(),
                o.getFoodcode());
    }

    public int removeById(Long id)
    {
        return jdbcTemplate.update("delete * from vegetableorder where id=?", id);
    }










    public int buy(Vegetableorder order){

        return jdbcTemplate.update("call add_order(?,?,?,?,?,?,?)",
                order.getFoodid(),order.getUserid(),order.getFoodnumber(),
                order.getSellerid(),order.getGetfoodtime(),order.getIsfinish(),
                order.getFoodcode());
    }

    public List<Order> getOrder(){
        return jdbcTemplate.query("call show_all_order()",new OrderMapper());
    }
}
