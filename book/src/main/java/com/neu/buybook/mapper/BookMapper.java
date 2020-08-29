package com.neu.buybook.mapper;

import com.neu.buybook.model.Book;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Book selectByPrimaryKey(Integer id);

    List<Book> selectAll(Book book);

    int updateByPrimaryKey(Book record);
    //批量插入
    void batchInsert(List<Book> list);
}