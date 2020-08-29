package com.neu.buybook.mapper;

import com.neu.buybook.model.SubType;

import java.util.List;

public interface SubTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubType record);

    SubType selectByPrimaryKey(Integer id);

    List<SubType> selectAll();

    int updateByPrimaryKey(SubType record);
    /**
     * 根据大类ID取所有小类
     */
    List<SubType> selectByMainType(Integer mainTypeId);
}