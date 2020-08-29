package com.neu.buybook.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Cart {
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Integer num;

    //一对一
    private Book book;


}