package com.neu.buybook.service.impl;

import com.neu.buybook.mapper.MainTypeMapper;
import com.neu.buybook.mapper.SubTypeMapper;
import com.neu.buybook.model.MainType;
import com.neu.buybook.model.SubType;
import com.neu.buybook.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private MainTypeMapper mainTypeMapper;

    @Autowired
    private SubTypeMapper subTypeMapper;

    @Override
    public List<MainType> selMainTypeAll() {
        return mainTypeMapper.selectAll();
    }

    @Override
    public List<SubType> selSubByMainType(Integer mainTypeId) {
        return subTypeMapper.selectByMainType(mainTypeId);
    }
}
