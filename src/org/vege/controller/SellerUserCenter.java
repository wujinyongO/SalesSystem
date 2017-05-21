package org.vege.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vege.model.Seller;
import org.vege.model.StatResp;
import org.vege.service.SellerService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by rustbell on 5/20/17.
 */
@Controller
@RequestMapping("/admin/usercenter")
public class SellerUserCenter {
    @Resource
    SellerService sellerService;

    //TODO 屏蔽密码
    @RequestMapping("/getinfo")
    @ResponseBody
    public Seller sellerInfo(HttpServletRequest req) {
        try {
            Long id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));
            Seller s = sellerService.getByID(id);
            s.setPassword(null);
            return s;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @RequestMapping("/disableaccount")
    @ResponseBody
    public StatResp disable(HttpServletRequest req) {
        try {
            Long id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));
            sellerService.disableById(id);
            //TODO 在登录检测里看这个参数并拒绝登录
            return new StatResp("ok", "成功");
        } catch (NumberFormatException e) {
            return new StatResp("error", "失败");
        }
    }

    @RequestMapping("/modifyinfo")
    @ResponseBody
    public StatResp modifyInfo(HttpServletRequest req) {
        try {
            Long id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));
            String storeName = req.getParameter(""); //TODO 协商参数名
            String phoneNum = req.getParameter(""); //TODO 协商参数名
            if (storeName != null) {
                sellerService.modifyNameById(storeName, id);
            } else if (phoneNum != null) {
                sellerService.modifyPhoneById(phoneNum, id);
            }
            return new StatResp("ok", "修改成功");
        } catch (NumberFormatException e) {
            return new StatResp("error", "修改失败");
        }
    }

    @RequestMapping("/modifypic")
    @ResponseBody
    public StatResp modifyPic(HttpServletRequest req) {
        try {
            Long id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));

            req.setCharacterEncoding("UTF-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            servletFileUpload.setFileSizeMax(30 * 1024 * 1024);
            servletFileUpload.setFileSizeMax(50 * 1024 * 1024);
            FileItem imageItem = null;
            String fileName = null;
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);

            for (FileItem fileItem : fileItems) {
                if (!(fileItem.isFormField())) {
//                    上传的是文件
                    UUID uuid = UUID.randomUUID();
                    fileName = fileItem.getName();
                    fileName = uuid + "_" + fileName;
                    imageItem = fileItem;
                }
            }
            
            if (imageItem != null) {
                Seller seller = sellerService.getByID(id);
                String pathdir = "/store/sellers/" + seller.getPhonenumber() + "/img/";
                File realpath = new File(req.getServletContext().getRealPath(pathdir));
                //FIXME /store/sellers等根文件夹需要自己创建
                imageItem.write(new File(realpath, fileName));

                String oldImg = seller.getWindowimg();
                String windowimg = "/store/sellers/" + seller.getPhonenumber() + "/img/" + fileName;
                seller.setWindowimg(windowimg);
                sellerService.modifyImgById(windowimg, id);
                //TODO 删除已有文件

                return new StatResp("ok", "修改成功");
            } else {
                return new StatResp("error", "修改失败");
            }

        } catch (NumberFormatException e) {
            return new StatResp("error", e.toString());
        } catch (FileUploadException e) {
            e.printStackTrace();
            return new StatResp("error", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new StatResp("error", e.toString());
        }
    }
}
