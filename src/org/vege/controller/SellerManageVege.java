package org.vege.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vege.model.StatResp;
import org.vege.model.Vege;
import org.vege.service.SellerService;
import org.vege.service.VegeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * Created by rustbell on 5/20/17.
 */
@Controller
@RequestMapping("/admin/manageVegetables")
public class SellerManageVege {

    @Resource
    VegeService vegeService;
    @Resource
    SellerService sellerService;

    // TODO 分页
    @RequestMapping("/list.json")
    @ResponseBody
    public List<Vege> getVegeListBySeller(HttpServletRequest req) {
        try {
            Long id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));
            return vegeService.getBySellerId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public StatResp addVegeToSeller(HttpServletRequest req) throws UnsupportedEncodingException {
        Long id;
        try {
            id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));
        } catch (NumberFormatException e) {
            return new StatResp("error", "invalid sellerid");
        }
        req.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        servletFileUpload.setFileSizeMax(30 * 1024 * 1024);
        servletFileUpload.setSizeMax(50 * 1024 * 1024);
        try {
            //parse json
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);

            Vege vege = new Vege();
            vege.setSellerid(id);
            //Foreach
            for (FileItem fileItem : fileItems) {
                //上传的是文件
                if (!(fileItem.isFormField()) && fileItem.getFieldName().equals("foodImg")) {
                    UUID uuid = UUID.randomUUID();
                    String fileName = uuid + "_" + fileItem.getName();

//                    String vegePic = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
//                            + "/" + id.toString() + "/img/" + fileName;

                    //create file
                    String path = "/store/sellers/" + sellerService.getByID(id).getPhonenumber() + "/img/";
                    File vegePicFile = new File(req.getServletContext().getRealPath(path) + fileName);
                    if (vegePicFile.createNewFile()) {
                        fileItem.write(vegePicFile);
                        vege.setFoodimg(path + fileName);
                    } else {
                        return new StatResp("error", "Create Img File Failed");
                    }
                } else {
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    if (fieldName.equals("foodName")) {
                        vege.setFoodname(value);
                    } else if (fieldName.equals("foodPrice")) {
                        vege.setFoodprice(Double.parseDouble(value));
                    } else if (fieldName.equals("priceType")) {
                        vege.setPricetype(value);
                    }
                }
            }

            if (vegeService.add(vege) == 1) {
                return new StatResp("ok", "添加成功");
            } else {
                return new StatResp("error", "添加成功");
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
            return new StatResp("error", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new StatResp("error", e.toString());
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public StatResp removeVegeByIdList(@RequestParam(value="deleteId", required = true) List<Long> idList) {
        for (Long id : idList) {
            vegeService.removeById(id);
        }
        return new StatResp("ok", "删除成功");
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public StatResp modifyVege(HttpServletRequest req) throws UnsupportedEncodingException {
        Long id;
        try {
            id = Long.parseLong((String) req.getSession().getAttribute("sellerid"));
        } catch (NumberFormatException e) {
            return new StatResp("error", "invalid sellerid");
        }
        req.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        servletFileUpload.setFileSizeMax(30 * 1024 * 1024);
        servletFileUpload.setSizeMax(50 * 1024 * 1024);
        try {
            //parse json
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);

            Vege vege = new Vege();
            vege.setSellerid(id);

            Long vid = 0L;
            //Foreach
            for (FileItem fileItem : fileItems) {
                //上传的是文件
                if (!(fileItem.isFormField()) && fileItem.getFieldName().equals("foodImg")) {
                    UUID uuid = UUID.randomUUID();
                    String fileName = uuid + "_" + fileItem.getName();

//                    String vegePic = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
//                            + "/" + id.toString() + "/img/" + fileName;

                    //create file
                    String path = "/store/sellers/" + sellerService.getByID(id).getPhonenumber() + "/img/";
                    File vegePicFile = new File(req.getServletContext().getRealPath(path) + fileName);
                    if (vegePicFile.createNewFile()) {
                        fileItem.write(vegePicFile);
                        vege.setFoodimg(path + fileName);
                    } else {
                        return new StatResp("error", "Create Img File Failed");
                    }
                } else {
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    if (fieldName.equals("vid")) {
                        vege.setFoodname(value);
                    }else if (fieldName.equals("foodName")) {
                        vege.setFoodname(value);
                    } else if (fieldName.equals("foodPrice")) {
                        vege.setFoodprice(Double.parseDouble(value));
                    } else if (fieldName.equals("priceType")) {
                        vege.setPricetype(value);
                    }
                }
            }

            if (vegeService.modify(vege, vid) == 1) {
                return new StatResp("ok", "修改成功");
            } else {
                return new StatResp("error", "修改成功");
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
            return new StatResp("error", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new StatResp("error", e.toString());
        }
    }


}
