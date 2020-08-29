package com.neu.buybook.mapper;

import com.neu.buybook.model.OrderItems;

import java.util.List;

public interface OrderItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItems record);

    OrderItems selectByPrimaryKey(Integer id);

    List<OrderItems> selectAll();

    int updateByPrimaryKey(OrderItems record);

    List<OrderItems> selByOrder(Integer orderId);
}