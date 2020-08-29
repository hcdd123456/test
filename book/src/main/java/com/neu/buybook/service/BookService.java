package com.neu.buybook.service;

import com.github.pagehelper.PageInfo;
import com.neu.buybook.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {

    PageInfo<Book> list(Integer currPage, Book book);

    Book update(Book book);

    void del(Integer id);

    void add(Book book);

    Book selById(Integer id);

    PageInfo<Book> selAll(Integer currPage, Book book);

    ResponseEntity<byte[]> export() throws IOException;

    void importBook(MultipartFile file);
}
