package com.neu.buybook.mapper;

import com.neu.buybook.model.MainType;

import java.util.List;

public interface MainTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MainType record);

    MainType selectByPrimaryKey(Integer id);

    List<MainType> selectAll();

    int updateByPrimaryKey(MainType record);
}