package org.vege.service;

import org.springframework.stereotype.Service;
import org.vege.dao.CustomerDao;
import org.vege.model.Customer;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rustbell on 5/21/17.
 */
@Service
public class CustomerService {
    @Resource
    CustomerDao customerDao;

    public List<Customer> getAll()
    {
        return customerDao.getAll();
    }

    public Customer getByID(Long id)
    {
        return customerDao.getByID(id);
    }

    public Customer getByPhone(String phone)
    {
        return customerDao.getByPhone(phone);
    }

}