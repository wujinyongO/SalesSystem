package org.vege.service;

import org.springframework.stereotype.Service;
import org.vege.dao.SellerDao;
import org.vege.model.Seller;

import javax.annotation.Resource;

/**
 * Created by rustbell on 5/20/17.
 */
@Service
public class SellerService {
    @Resource
    SellerDao sellerDao;

//    public List<Seller> getAll()
//    {
//        return sellerDao.getAll();
//    }

    public Seller getByID(Long id)
    {
        return sellerDao.getByID(id);
    }

    public Seller getByPhone(String phone)
    {
        return sellerDao.getByPhone(phone);
    }

    public int disableById(Long id) { return sellerDao.disableById(id); }

    public int modifyNameById(String name, Long id) { return sellerDao.modifyNameById(name, id); }
    public int modifyPhoneById(String phone, Long id) { return sellerDao.modifyPhoneById(phone, id); }
    public int modifyImgById(String name, Long id) { return sellerDao.modifyImgById(name, id); }

}
