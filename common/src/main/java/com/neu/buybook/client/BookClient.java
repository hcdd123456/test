package com.neu.buybook.client;

import com.neu.buybook.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 调用Book微服务的接口
 * name:注册的微服务的名字
 * GetMapping:微服务中的controller的RequestMapping
 * fallback: 配置熔断类
 * @Component注解表明一个类会作为组件类，并告知Spring要为这个类创建bean。@Component 作用于类，@Bean作用于方法。
 */
@FeignClient(name="book",fallback = BookClient.FallBack.class)
public interface BookClient {

    //需要访问的微服务的接口
    @GetMapping("/book/selById/{id}")
    Book selById(@PathVariable("id") Integer id);

    /**
     * 处理熔断的类
     */
    @Component
    static class FallBack implements BookClient{
        @Override
        public Book selById(Integer id) {
            return null;
        }
    }
}
