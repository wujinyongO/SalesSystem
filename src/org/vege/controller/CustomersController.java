package org.vege.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vege.model.Customer;
import org.vege.service.CustomerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Cier on 2017/5/20.
 */
@Controller
public class CustomersController {

    @Resource
    CustomerService cusService;

    @RequestMapping("/getallcustomers")
    @ResponseBody
    public List<Customer> customers(){
        List<Customer> list=cusService.getAll();
        return list;
    }

}
