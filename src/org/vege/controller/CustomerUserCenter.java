package org.vege.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rustbell on 5/21/17.
 */
@Controller
@RequestMapping("userCenter")
public class CustomerUserCenter {
//    @RequestMapping("/modifyinfo")
//    @ResponseBody
//    public StatResp modifyInfo(HttpServletRequest req) {
//        try {
//            Long id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));
//            String storeName = req.getParameter(""); //TODO 协商参数名
//            String phoneNum = req.getParameter(""); //TODO 协商参数名
//            if (storeName != null) {
//                sellerService.modifyNameById(storeName, id);
//            } else if (phoneNum != null) {
//                sellerService.modifyPhoneById(phoneNum, id);
//            }
//            return new StatResp("ok", "修改成功");
//        } catch (NumberFormatException e) {
//            return new StatResp("error", "修改失败");
//        }
//    }
}
