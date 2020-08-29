package com.neu.buybook.service.impl;

import com.neu.buybook.client.BookClient;
import com.neu.buybook.mapper.CartMapper;
import com.neu.buybook.model.Book;
import com.neu.buybook.model.Cart;
import com.neu.buybook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private BookClient bookClient;

    @Override
    public void add(Cart cart) {
        Cart qCart = cartMapper.selByUserBook(cart);
        if(qCart == null){//没有记录，新增
            cartMapper.insert(cart);
        }else{//有记录，更新数量
            qCart.setNum(qCart.getNum() + cart.getNum());
            cartMapper.updateByPrimaryKey(qCart);
        }
    }

    @Override
    public Integer selNumByUser(Integer userId) {
        return cartMapper.selNumByUser(userId);
    }

    @Override
    public List<Cart> selByUser(Integer userId) {
        //遍历购物车
        List<Cart> list = cartMapper.selByUser(userId);
        for(Cart c:list){
            //通过c.getBookId()调用book微服务,取出book对象
            Book book = bookClient.selById(c.getBookId());
            c.setBook(book);
        }
        return list;
    }

    @Override
    public void update(Cart cart) {
        cartMapper.updateByPrimaryKey(cart);
    }

    /**
     * 清除用户购物车记录
     * @param userId
     */
    @Override
    public void delByUser(Integer userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cartMapper.delByUser(cart);
    }

    @Override
    public void delById(Integer id) {
        cartMapper.deleteByPrimaryKey(id);
    }
}
