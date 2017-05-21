package org.vege.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vege.model.Customer;
import org.vege.model.StatResp;
import org.vege.service.CustomerService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by rustbell on 5/21/17.
 */
@Controller
@RequestMapping("/customerAuth")
public class LoginAuthCustomer {

    @Resource
    CustomerService customerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public StatResp login(HttpServletRequest req) {
        String usrPhone = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("username: " + usrPhone);
        System.out.println("password: " + password);

        Customer s = customerService.getByPhone(usrPhone);
        //用户存在，验证密码
        if (s != null) {
            //  TODO 后端加密密码
            String p = s.getPassword();
            if (password.equals(p)) {
                //登录成功
                req.getSession().setAttribute("customerid", s.getId().toString());
                return new StatResp("ok", "登录成功");
            } else {
                //密码错误
                return new StatResp("error", "密码错误！");
            }
        }

        //用户不存在
        else {
            return new StatResp("error", "用户不存在！");
        }

    }

    @RequestMapping("/logout")
    @ResponseBody
    public StatResp logout(HttpServletRequest req)
    {
        if (req.getSession().getAttribute("customerid") != null) {
            req.getSession().removeAttribute("customerid");
            return new StatResp("ok", "注销成功");
        }
        else {
            return new StatResp("error", "没有登录！");
        }
    }
}
