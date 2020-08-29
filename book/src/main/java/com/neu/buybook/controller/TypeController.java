package com.neu.buybook.controller;

import com.neu.buybook.model.MainType;
import com.neu.buybook.model.SubType;
import com.neu.buybook.service.TypeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图书类别模块
 */
@RequestMapping("/type")
@RestController
public class TypeController {

    @Resource
    private TypeService typeService;

    @RequestMapping("selMainType")
    public List<MainType> selMainType(){
        return typeService.selMainTypeAll();
    }

    /**
     * 根据大类ID取小类
     */
    @RequestMapping("selSubType/{mainTypeId}")
    public List<SubType> selSubType(@PathVariable Integer mainTypeId){
        return typeService.selSubByMainType(mainTypeId);
    }
}
