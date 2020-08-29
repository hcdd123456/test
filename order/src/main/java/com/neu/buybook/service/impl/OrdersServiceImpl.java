package com.neu.buybook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import com.neu.buybook.client.BookClient;
import com.neu.buybook.mapper.CartMapper;
import com.neu.buybook.mapper.OrderItemsMapper;
import com.neu.buybook.mapper.OrdersMapper;
import com.neu.buybook.model.Cart;
import com.neu.buybook.model.OrderItems;
import com.neu.buybook.model.Orders;
import com.neu.buybook.service.OrdersService;
import com.neu.buybook.util.GeneratorCode;
import com.neu.buybook.vo.CollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private BookClient bookClient;

    /**
     * 添加订单
     * Transactional: 开启事务
     * @param orders
     * @return
     */
    @Override
    @Transactional
    public String add(Orders orders) {
        //生成订单编号
        String code = GeneratorCode.generatorOrder();
        orders.setCode(code);
        orders.setStatus("0");
        orders.setOrderdate(new Date());
        ordersMapper.insert(orders);
        //获取新增的订单的ID
        int id = orders.getId();
        for(OrderItems item:orders.getOrderItems()){
            item.setOrderId(id);
            //新增订单明细
            orderItemsMapper.insert(item);
        }

        //删除已新增订单购物车记录
        for(OrderItems item:orders.getOrderItems()){
            Cart cart = new Cart();
            cart.setBookId(item.getBookId());
            cart.setUserId(orders.getUserId());
            cartMapper.delByUser(cart);
        }

        return code;
    }

    @Override
    public PageInfo<Orders> list(Integer currPage, Orders orders) {
        if(currPage == null) currPage = 1;
        PageHelper.startPage(currPage,8);
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersMapper.selectAll(orders));
        return pageInfo;
    }

    @Override
    public List<OrderItems> selOrderItemsByOrder(Integer orderId) {
        List<OrderItems> list = orderItemsMapper.selByOrder(orderId);
        for(OrderItems o: list){
            o.setBook(bookClient.selById(o.getBookId()));
        }
        return list;
    }

    /**
     * 根据年份和用户统计每月帐单
     * @param collectVO
     * @return
     */
    @Override
    public List<Map<String, Object>> selSale(CollectVO collectVO) {
        return ordersMapper.selSale(collectVO);
    }


}
