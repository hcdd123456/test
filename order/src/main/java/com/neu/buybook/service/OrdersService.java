package com.neu.buybook.service;

import com.github.pagehelper.PageInfo;
import com.neu.buybook.model.OrderItems;
import com.neu.buybook.model.Orders;
import com.neu.buybook.vo.CollectVO;

import java.util.List;
import java.util.Map;

public interface OrdersService {

    String add(Orders orders);

    PageInfo<Orders> list(Integer currPage, Orders orders);

    //根据订单号查询订单明细
    List<OrderItems> selOrderItemsByOrder(Integer orderId);

    List<Map<String,Object>> selSale(CollectVO collectVO);
}
