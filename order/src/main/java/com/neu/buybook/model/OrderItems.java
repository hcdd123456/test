package com.neu.buybook.model;

public class OrderItems {
    private Integer id;

    private Integer orderId;

    private Integer bookId;

    private Float price;

    private Integer num;

    //一对一
    private Book book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", bookId=" + bookId +
                ", price=" + price +
                ", num=" + num +
                ", book=" + book +
                '}';
    }
}