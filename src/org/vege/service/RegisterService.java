package org.vege.service;

import org.springframework.stereotype.Service;
import org.vege.dao.RegisterDao;
import org.vege.model.Customer;
import org.vege.model.Seller;

import javax.annotation.Resource;

/**
 * Created by Cier on 2017/5/20.
 */
@Service
public class RegisterService {
    @Resource
    RegisterDao regDao;

    public int CusRegist(Customer cus){
        if(!regDao.check_cus_exist(cus)){
            return 0;
        }
        return regDao.CusRegist(cus);
    }

    public int SellerRegist(Seller seller){
        if(!regDao.check_seller_exist(seller)){
            return 0;
        }
        return regDao.SellerRegist(seller);
    }
}
