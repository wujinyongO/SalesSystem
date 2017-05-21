package org.vege.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vege.model.Customer;
import org.vege.model.CustomerMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rustbell on 5/21/17.
 */
@Repository
public class CustomerDao {
    @Resource
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAll()
    {
        return jdbcTemplate.query("select * from customers", new CustomerMapper());
    }

    public Customer getByID(Long id)
    {
        String[] arg = new String[1];
        arg[0] = id.toString();
        List<Customer> query = jdbcTemplate.query("select * from customers where id=?", arg, new CustomerMapper());
        if (query.isEmpty())
            return null;
        else return query.get(0);
    }

    public Customer getByPhone(String phone)
    {
        String[] arg = new String[1];
        arg[0] = phone;
        List<Customer> query = jdbcTemplate.query("select * from customers where phonenumber=?", arg, new CustomerMapper());
        if (query.isEmpty())
            return null;
        else return query.get(0);
    }

}
