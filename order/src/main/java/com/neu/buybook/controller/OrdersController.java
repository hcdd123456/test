package com.neu.buybook.controller;

import com.github.pagehelper.PageInfo;
import com.neu.buybook.model.OrderItems;
import com.neu.buybook.model.Orders;
import com.neu.buybook.service.OrdersService;
import com.neu.buybook.vo.CollectVO;
import com.neu.buybook.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    /**
     * 分页查询
     * @param currPage
     * @return
     */
    @RequestMapping("list/{currPage}")
    public PageInfo<Orders> list(@PathVariable Integer currPage, @RequestBody Orders orders){
        return ordersService.list(currPage,orders);
    }

    @RequestMapping("add")
    public ResultVO add(@RequestBody Orders orders){
        String code = ordersService.add(orders);
        ResultVO resultVO = new ResultVO(code,200);
        return resultVO;
    }

    @RequestMapping("selOrderItems/{id}")
    public List<OrderItems> selOrderItems(@PathVariable Integer id){
        return ordersService.selOrderItemsByOrder(id);
    }

    @RequestMapping("collectSale")
    public List<Map<String,Object>> collectSale(@RequestBody CollectVO collectVO){
        return ordersService.selSale(collectVO);
    }
}
