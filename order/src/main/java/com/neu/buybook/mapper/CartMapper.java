package com.neu.buybook.mapper;

import com.neu.buybook.model.Cart;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    Cart selectByPrimaryKey(Integer id);

    List<Cart> selectAll();

    int updateByPrimaryKey(Cart record);

    Cart selByUserBook(Cart cart);

    /**
     * 根据用户取购物车商品数量
     * @param userId
     * @return
     */
    Integer selNumByUser(Integer userId);

    /**
     * 根据用户取购物车数据
     * @param userId
     * @return
     */
    List<Cart> selByUser(Integer userId);

    /**
     * 根据用户ID和书ID删除购物车记录
     * @param cart
     */
    void delByUser(Cart cart);
}