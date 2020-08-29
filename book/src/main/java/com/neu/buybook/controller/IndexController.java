package com.neu.buybook.controller;

import com.github.pagehelper.PageInfo;
import com.neu.buybook.model.Book;
import com.neu.buybook.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 网站首页
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private BookService bookService;

    @RequestMapping("selBook/{currPage}")
    public PageInfo<Book> selBook(@PathVariable Integer currPage){
        return bookService.selAll(currPage,new Book());
    }

}
