package org.vege.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vege.model.Seller;
import org.vege.model.StatResp;
import org.vege.service.SellerService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by rustbell on 5/21/17.
 */
@Controller
@RequestMapping("/sellerAuth")
public class LoginAuthSeller {

    @Resource
    SellerService sellerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public StatResp login(HttpServletRequest req) {
        String usrPhone = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("username: " + usrPhone);
        System.out.println("password: " + password);

        Seller s = sellerService.getByPhone(usrPhone);
        //用户存在，验证密码
        if (s != null) {
            //  TODO 后端加密密码
            String p = s.getPassword();
            if (password.equals(p)) {
                //登录成功
                req.getSession().setAttribute("sellerid", s.getId().toString());
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
        if (req.getSession().getAttribute("sellerid") != null) {
            req.getSession().removeAttribute("sellerid");
            return new StatResp("ok", "注销成功");
        }
        else {
            return new StatResp("error", "没有登录！");
        }
    }
}
