package com.neu.buybook.mapper;

import com.neu.buybook.model.Orders;
import com.neu.buybook.vo.CollectVO;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    Orders selectByPrimaryKey(Integer id);

    List<Orders> selectAll(Orders orders);

    int updateByPrimaryKey(Orders record);

    List<Map<String,Object>> selSale(CollectVO collectVO);
}