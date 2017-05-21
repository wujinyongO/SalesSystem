package org.vege.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vege.model.Vege;
import org.vege.service.VegeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rustbell on 5/20/17.
 */
@Controller
@RequestMapping("/mall")
public class Mall {

    @Resource
    VegeService vegeService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Vege> getVegeListAll() {
        //TODO 分页传参
        return vegeService.getAll();
    }
}
