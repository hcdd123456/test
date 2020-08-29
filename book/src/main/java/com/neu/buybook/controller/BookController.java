package com.neu.buybook.controller;

import com.github.pagehelper.PageInfo;
import com.neu.buybook.model.Book;
import com.neu.buybook.service.BookService;
import com.neu.buybook.vo.ResultVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 图书模块
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    /**
     * 分页查询
     * @param currPage
     * @return
     */
    @RequestMapping("list/{currPage}")
    public PageInfo<Book> list(@PathVariable Integer currPage, @RequestBody Book book){
        System.out.println(book);
        return bookService.list(currPage,book);
    }

    /**
     * 修改
     * @param book
     * @return
     */
    @RequestMapping("update")
    public String update(@RequestBody Book book){
        bookService.update(book);
        return "succ";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    public String del(@PathVariable Integer id){
        bookService.del(id);
        return "succ";
    }

    /**
     * 新增
     * @param book
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody Book book){
        bookService.add(book);
        return "succ";
    }

    /**
     * 根据ID加载记录
     * @param id
     * @return
     */
    @RequestMapping("selById/{id}")
    public Book selById(@PathVariable Integer id){
        return bookService.selById(id);
    }

    /**
     * 导出
     * @return
     * @throws IOException
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws IOException {
        return bookService.export();
    }

    @RequestMapping("import")
    public ResultVO importBook(MultipartFile file){
        bookService.importBook(file);
        ResultVO resultVO = new ResultVO("",200);
        return resultVO;
    }
}
