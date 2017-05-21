package org.vege.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vege.model.Customer;
import org.vege.model.Seller;
import org.vege.model.StatResp;
import org.vege.service.RegisterService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Cier on 2017/5/20.
 */
@Controller
public class RegisterController {
    @Resource
    RegisterService regService;

    @RequestMapping(value = "/CustomerRegist",method = RequestMethod.POST)
    @ResponseBody
    public StatResp CusRegist(HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("public StatResp CusRegist(HttpServletRequest request)");
        request.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload servletFileUpload=new ServletFileUpload(factory);
        servletFileUpload.setFileSizeMax(30*1024*1024);
        servletFileUpload.setFileSizeMax(50*1024*1024);
        Customer customers=new Customer();
        FileItem imageItem=null;
        String fileName=null;

        try{
            List<FileItem> fileItems=servletFileUpload.parseRequest(request);

            for(FileItem fileItem:fileItems){
                if(!(fileItem.isFormField())){
//                    上传的是文件
                    UUID uuid=UUID.randomUUID();
                    fileName= fileItem.getName();
                    fileName=uuid+"_"+fileName;
                    imageItem=fileItem;
                }else{
                    String fieldName=fileItem.getFieldName();
                    String value=fileItem.getString("UTF-8");
                    System.out.println("customers: "+fieldName+" "+value);

                    if(fieldName.equals("username")){
                        customers.setName(value);
                    }else if(fieldName.equals("password")){
                        customers.setPassword(value);
                    }else if(fieldName.equals("windowName")){

                    }else if(fieldName.equals("phoneNumber")){
                        customers.setPhonenumber(value);
                    }else if(fieldName.equals("windowImg")){
                        customers.setImageurl(value);
                    }else if(fieldName.equals("address")){
                        customers.setImageurl(value);
                    }else{

                    }
                }
            }
            String windowimg="/store/customers/"+customers.getPhonenumber()+"/img/" + fileName;
            customers.setImageurl(windowimg);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StatResp response=new StatResp();
        int rep=regService.CusRegist(customers);
        if(rep!=0 && imageItem!=null){
            File file=new File(request.getServletContext().getRealPath("/store/customers/")+customers.getPhonenumber());
                    if(!file.exists()){
                        System.out.println("customers not exist,create path="+file.toPath());
                        file.mkdir();
                        File imgdir=new File(request.getServletContext().getRealPath("/store/customers/")+customers.getPhonenumber() + "/img");
                        imgdir.mkdir();
                    }
            try {
                imageItem.write(new File(file,fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setStatus("ok");
            response.setMsg("买菜会员注册成功");
        }else{
            response.setStatus("error");
            response.setMsg("该会员已存在");
        }

        return response;
    }

    @RequestMapping(value = "/SellerRegist",method = RequestMethod.POST)
    @ResponseBody
    public StatResp SellerReg(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload servletFileUpload=new ServletFileUpload(factory);
        servletFileUpload.setFileSizeMax(30*1024*1024);
        servletFileUpload.setFileSizeMax(50*1024*1024);
        Seller seller=new Seller();
        FileItem imageItem=null;
        String fileName=null;

        try {
            List<FileItem> fileItems=servletFileUpload.parseRequest(request);
            for(FileItem fileItem:fileItems){
                if(!(fileItem.isFormField())){
//                    上传的是文件
                    UUID uuid=UUID.randomUUID();
//                    获取文件名称
                    fileName=fileItem.getName();
                    fileName=uuid+"_"+fileName;
                    imageItem=fileItem;
                }else{
                    String fieldName=fileItem.getFieldName();
                    String value=fileItem.getString();
                    System.out.println("seller: "+fieldName+" "+value);
                    if(fieldName.equals("userName")){
                        seller.setSellername(value);
                    }else if(fieldName.equals("password")){
                        seller.setPassword(value);
                    }else if(fieldName.equals("winName")){
                        seller.setWindowname(value);
                    }else if(fieldName.equals("photoNum")){
                        seller.setPhonenumber(value);
                    }else if(fieldName.equals("winImg")){
                        seller.setWindowimg(value);
                    }else if(fieldName.equals("winAdr")){
                        //
                    }else{
                        System.out.println("seller controller,seller do not have this key="+value);
                    }
                }
            }
            String windowimg = "/store/sellers/"+seller.getPhonenumber()+"/img/" + fileName;
            seller.setWindowimg(windowimg);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StatResp response=new StatResp();
        int req=regService.SellerRegist(seller);
        System.out.println("req="+req);
        if(req!=0 && imageItem!=null){
                    File file=new File(request.getServletContext().getRealPath("/store/sellers/")+seller.getPhonenumber());
                    if(!file.exists()) {
                        System.out.println("seller file not exist,create path="+file.getPath());
                        file.mkdir();
                        File imgdir=new File(request.getServletContext().getRealPath("/store/sellers/")+seller.getPhonenumber() + "/img");
                        imgdir.mkdir();
                    }
            try {
                //FIXME /store/sellers等根文件夹需要自己创建
                imageItem.write(new File(file,fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setStatus("ok");
            response.setMsg("商家注册成功");
        }else{
            response.setStatus("error");
            response.setMsg("该商家已存在");
        }

        return response;
    }
}
