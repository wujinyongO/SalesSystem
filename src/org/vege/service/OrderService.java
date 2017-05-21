package org.vege.service;

import org.springframework.stereotype.Service;
import org.vege.dao.OrderDao;
import org.vege.model.Order;
import org.vege.model.Vegetableorder;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rustbell on 5/21/17.
 */
@Service
public class OrderService {
    @Resource
    OrderDao orderDao;

    public List<Order> getBySellerId(Long id) { return orderDao.getBySellerID(id); }
    public List<Order> getByCustomerId(Long id) { return orderDao.getByCustomerId(id); }
    public int add(Order o) { return orderDao.add(o); }
    public int removeById(Long id) { return orderDao.removeById(id); }













    public int buyVegetable(List<Vegetableorder> list){
        for(int i=0;i<list.size();i++){
            buyVegetable(list.get(i));
        }
        return 1;
    }

    public int buyVegetable(Vegetableorder vege){
        orderDao.buy(vege);
        return 1;
    }

    public List<Order> getOrder(){
        return orderDao.getOrder();
    }
}
