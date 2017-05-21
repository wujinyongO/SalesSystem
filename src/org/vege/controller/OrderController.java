package org.vege.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vege.model.Order;
import org.vege.model.StatResp;
import org.vege.model.Vegetableorder;
import org.vege.service.OrderService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rustbell on 5/21/17.
 */
@Controller
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping("/listBySeller")
    @ResponseBody
    public List<Order> getOrderBySellerId(HttpServletRequest req) {
        //TODO 分页传参
        try {
            Long id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));
            return orderService.getBySellerId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    @RequestMapping("/listByCustomer")
    @ResponseBody
    public List<Order> getOrderByCustomerId(HttpServletRequest req) {
        //TODO 分页传参
        try {
            Long id = Long.parseLong((String) req.getSession().getAttribute("customerid"));
            return orderService.getByCustomerId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }













    List<Vegetableorder> list=new ArrayList<>();
    @RequestMapping(value = "/addtobuycar",method = RequestMethod.POST)
    @ResponseBody
    public StatResp add(HttpServletRequest request,Long id,String foodname,Long foodprice,Long foodnumber,Long sellid) throws UnsupportedEncodingException {
        StatResp response=new StatResp();
        request.setCharacterEncoding("UTF-8");

        Vegetableorder order=new Vegetableorder();
        order.setFoodid(id);
        order.setFoodnumber(foodnumber);
        order.setSellerid(sellid);
        order.setUserid(Long.parseLong((String) request.getSession().getAttribute("customerid")));
        order.setGetfoodtime(String.valueOf(System.currentTimeMillis()));
        order.setIsfinish(0L);
        order.setFoodcode(String.valueOf(System.currentTimeMillis()));
        System.out.println("add_to_buycar"+order.getFoodid()+" "+order.getFoodnumber());


        list.add(order);
        orderService.buyVegetable(order);

        response.setStatus("ok");
        response.setMsg("成功购买1件商品");
        return response;
    }

    @RequestMapping(value = "/getorder",method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getOrder(HttpServletRequest request){
        System.out.println("public List<Vegetableorder> getOrder");
        return orderService.getOrder();

    }

    @RequestMapping(value = "/buy",method = RequestMethod.POST)
    @ResponseBody
    public StatResp buy(HttpServletRequest request){
        StatResp response=new StatResp();
        orderService.buyVegetable(list);

        list.clear();
        return response;
    }
}