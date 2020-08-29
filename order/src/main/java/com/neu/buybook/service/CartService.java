package com.neu.buybook.service;

import com.neu.buybook.model.Cart;

import java.util.List;

public interface CartService {

    void add(Cart cart);

    Integer selNumByUser(Integer userId);

    List<Cart> selByUser(Integer userId);

    void update(Cart cart);

    //删除用户的购物车记录
    void delByUser(Integer userId);
    //根据主键删除购物车
    void delById(Integer id);
}
