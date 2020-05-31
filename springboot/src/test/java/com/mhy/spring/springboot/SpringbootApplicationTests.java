package com.mhy.spring.springboot;

import com.mhy.spring.bean.Dog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringbootApplicationTests {

    @Autowired //注意：此处不加public static 容易报错
   public static Dog dog;

    @Test
    public void contextLoads() {
        System.out.println(dog);
    }

}
