package com.chixing;

import com.chixing.mapper.Customermapper;
import com.chixing.pojo.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;

@SpringBootTest
class SpringbootJwtApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private Customermapper customermapper;

    @Cacheable(value="m30",
            key = "",
            unless = "")
    @Test
    public void getHot(){
        Customer customer = new Customer();

    }
}
