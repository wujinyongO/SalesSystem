package org.vege.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vege.model.Seller;
import org.vege.model.SellerMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rustbell on 5/20/17.
 */
@Repository
public class SellerDao {
    @Resource
    JdbcTemplate jdbcTemplate;

//    public List<Seller> getAll()
//    {
//        return jdbcTemplate.query("select * from sellers", new SellerMapper());
//    }

    public Seller getByID(Long id) {
        System.out.println("id:" + id.toString());
        String[] arg = new String[1];
        arg[0] = id.toString();
        List<Seller> query = jdbcTemplate.query("select * from sellers where id=?", arg, new SellerMapper());
        if (query.isEmpty())
            return null;
        else return query.get(0);
    }

    public Seller getByPhone(String phone) {
        String[] arg = new String[1];
        arg[0] = phone;
        List<Seller> query = jdbcTemplate.query("select * from sellers where phoneNumber=?", arg, new SellerMapper());
        if (query.isEmpty())
            return null;
        else return query.get(0);
    }


    public int disableById(Long id) {
        return jdbcTemplate.update("update sellers set state=? where id=?", -1L, id);
    }


    public int modifyNameById(String name, Long id) {
        return jdbcTemplate.update("update sellers set windowName=? where id=?", name, id);
    }

    public int modifyPhoneById(String phone, Long id) {
        return jdbcTemplate.update("update sellers set phoneNumber=? where id=?", phone, id);
    }

    public int modifyImgById(String img, Long id) {
        return jdbcTemplate.update("update sellers set windowImg=? where id=?", img, id);
    }

}
