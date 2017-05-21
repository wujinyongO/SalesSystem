package org.vege.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vege.model.Customer;
import org.vege.model.CustomerMapper;
import org.vege.model.Seller;
import org.vege.model.SellerMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Cier on 2017/5/20.
 */
@Repository
public class RegisterDao {
    @Resource
    JdbcTemplate jdbcTemplate;

    public int CusRegist(Customer cus){
        return jdbcTemplate.update("call add_customers(?,?,?,?,?)",
                cus.getName(),cus.getPassword(),cus.getPhonenumber(),cus.getImageurl(),cus.getAddress());
    }

    public boolean check_cus_exist(Customer cus){
        List<Customer> list=jdbcTemplate.query("select * from customers where phonenumber=?",new CustomerMapper(),cus.getPhonenumber());
        System.out.println("list.size="+list.size());
        return list.size()==0;
    }

    public int SellerRegist(Seller seller){
        return jdbcTemplate.update("call add_sellers(?,?,?,?,?,?)",
                seller.getPhonenumber(),seller.getWindowimg(),seller.getPassword(),
                seller.getState(),seller.getSellername(),seller.getWindowname());
    }

    public boolean check_seller_exist(Seller seller){
        List<Seller> list=jdbcTemplate.query("select * from sellers where phonenumber=?",new SellerMapper(),seller.getPhonenumber());
        System.out.println("list.size="+list.size());
        return list.size()==0;
    }
}
