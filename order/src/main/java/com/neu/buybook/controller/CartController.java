package com.neu.buybook.controller;

import com.neu.buybook.model.Cart;
import com.neu.buybook.service.CartService;
import com.neu.buybook.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车模块
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    @RequestMapping("add")
    public ResultVO add(@RequestBody Cart cart){
        cartService.add(cart);
        ResultVO resultVO = new ResultVO("操作成功",200);
        return resultVO;
    }

    /**
     * 根据用户取购物车商品数量
     * @param userId
     * @return
     */
    @RequestMapping("selNumByUser/{userId}")
    public ResultVO selNumByUser(@PathVariable Integer userId){
        Integer sum = cartService.selNumByUser(userId);
        ResultVO resultVO = new ResultVO("",sum);
        return resultVO;
    }

    @RequestMapping("selByUser/{userId}")
    public List<Cart> selByUser(@PathVariable Integer userId){
        return cartService.selByUser(userId);
    }

    @RequestMapping("update")
    public ResultVO update(@RequestBody Cart cart){
        cartService.update(cart);
        ResultVO resultVO = new ResultVO("",200);
        return resultVO;
    }

    @RequestMapping("delByUser/{userId}")
    public ResultVO delByUser(@PathVariable Integer userId){
        cartService.delByUser(userId);
        ResultVO resultVO = new ResultVO("",200);
        return resultVO;
    }

    @RequestMapping("delById/{id}")
    public ResultVO delById(@PathVariable Integer id){
        cartService.delById(id);
        ResultVO resultVO = new ResultVO("",200);
        return resultVO;
    }


}
