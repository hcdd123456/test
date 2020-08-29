package com.neu.buybook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.buybook.mapper.BookMapper;
import com.neu.buybook.model.Book;
import com.neu.buybook.service.BookService;
import com.neu.buybook.util.PoiUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 分页查询
     * @param currPage
     * @return
     */
    @Override
    public PageInfo<Book> list(Integer currPage,Book book) {
        if(currPage == null) currPage = 1;
        PageHelper.startPage(currPage,5);
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.selectAll(book));
        return pageInfo;
    }

    /**
     * CachePut保证方法被调用，又希望结果被缓存。
     *与@Cacheable区别在于是否每次都调用方法，常用于更新
     * @param book
     */
    @Override
    @CachePut(value="book",key="#book.id")
    public Book update(Book book) {
        bookMapper.updateByPrimaryKey(book);
        return book;
    }

    /**
     * CacheEvict   删除,从缓存中删除相关的key-value,用来标注在需要清除缓存元素的方法或类上
     * @param id
     */
    @Override
    @CacheEvict(value="book",key="#id")
    public void del(Integer id) {
        bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Book book) {
        book.setBdate(new Date());
        book.setNewBook("1");
        bookMapper.insert(book);
    }

    /**
     * Cacheable如果缓存没有值,则执行方法并缓存数据,如果缓存有值,则从缓存中获取值
     * value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
     * key：缓存的key，默认为空，同上
     * unless参数的作用是：函数返回值符合表达式条件的，veto（否决）、不缓存
     *换句话说，函数返回值符合条件的排除掉、只缓存其余不符合条件的
     * 根据主键加载
     * @param id
     * @return
     */
    @Override
    @Cacheable(value="book",key="#id",unless="#result==null")
    public Book selById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Book> selAll(Integer currPage, Book book) {
        if(currPage == null) currPage = 1;
        PageHelper.startPage(currPage,12);
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.selectAll(book));
        return pageInfo;
    }

    /**
     * 导出
     * @return
     */
    @Override
    public ResponseEntity<byte[]> export() throws IOException {
        return PoiUtils.exportBookExcel(bookMapper.selectAll(new Book()));
    }

    @Override
    public void importBook(MultipartFile file) {
        //原文件名
        String originalName = file.getOriginalFilename();
        System.out.println(originalName);
        Workbook workbook = null;
        InputStream fis = null;
        try {
            //根据不同格式的excel文件创建Workbook对象
            fis = file.getInputStream();
            if (originalName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (originalName.endsWith(".xls") || originalName.endsWith(".et")) {
                workbook = new HSSFWorkbook(fis);
            }
            /* 读EXCEL文字内容 */
            // 获取第一个sheet表，也可使用sheet表名获取
            Sheet sheet = workbook.getSheetAt(0);
            // 获取行
            Iterator<Row> rows = sheet.rowIterator();
            Row row;
            Cell cell;
            int index = -1;
            List<Book> list = new ArrayList<Book>();
            while (rows.hasNext()) {//遍历每一行
                row = rows.next();
                index++;
                if(index == 0) continue; //如果是标题行,则跳过
                // 获取每一个单元格
                // 获取单元格
                Cell c0 = row.getCell(0); //书名
                Cell c1 = row.getCell(1); //大类
                Cell c2 = row.getCell(2); //小类
                Cell c3 = row.getCell(3); //ISBN
                Cell c4 = row.getCell(4); //原价
                Cell c5 = row.getCell(5); //现价
                Cell c6 = row.getCell(6); //出版社
                Cell c7 = row.getCell(7); //出版日期
                Cell c8 = row.getCell(8); //简介
                String bname,mainType,subType,isbn=null,pubisher,detail;
                Double oriPrice,currPrice;
                Date pubDate = null;
                bname = c0.getStringCellValue();
                mainType = c1.getStringCellValue();
                subType = c2.getStringCellValue();
                if(c3 != null){
                    c3.setCellType(Cell.CELL_TYPE_STRING);
                    isbn = c3.getStringCellValue();
                }
                pubisher = c6.getStringCellValue();
                detail = c8.getStringCellValue();

                oriPrice = c4.getNumericCellValue();
                currPrice = c5.getNumericCellValue();
                if(c7!=null){
                    pubDate = (Date) c7.getDateCellValue();
                }
                System.out.println(bname+","+mainType+","+subType+","+isbn+","+pubisher+
                        ","+detail+","+oriPrice+","+currPrice+","+pubDate);

                Book book = new Book();
                book.setBname(bname);
                book.setMainType(mainType);
                book.setSubType(subType);
                book.setIsbn(isbn);
                book.setPublisher(pubisher);
                book.setDetail(detail);
                book.setOriPrice(oriPrice.floatValue());
                book.setCurPrice(currPrice.floatValue());
                book.setPubDate(pubDate);
                book.setBdate(new Date());
                book.setNewBook("1");

                list.add(book);
            }

            bookMapper.batchInsert(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
