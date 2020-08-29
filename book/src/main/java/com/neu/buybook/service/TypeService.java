package com.neu.buybook.service;

import com.neu.buybook.model.MainType;
import com.neu.buybook.model.SubType;

import java.util.List;

public interface TypeService {

    List<MainType> selMainTypeAll();

    /**
     * 根据大类ID取所有小类
     */
    List<SubType> selSubByMainType(Integer mainTypeId);
}
