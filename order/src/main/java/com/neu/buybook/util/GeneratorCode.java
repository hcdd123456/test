package com.neu.buybook.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GeneratorCode {

    /**
     * 生成订单编号
     * @return
     */
    public static String generatorOrder(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        date = date.replaceAll(" ","");
        date = date.replaceAll(":","");
        String result = date + UUID.randomUUID().toString();
        result = result.replaceAll("-","").substring(0,24);
        return result;
    }

    public static void main(String[] args){
        String code = GeneratorCode.generatorOrder();
        System.out.println(code);
    }

}
