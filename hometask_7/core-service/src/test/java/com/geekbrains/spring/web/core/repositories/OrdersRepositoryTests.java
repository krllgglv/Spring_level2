package com.geekbrains.spring.web.core.repositories;

import com.geekbrains.spring.web.core.repositories.OrdersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;




@DataJpaTest
@ActiveProfiles("test")

public class OrdersRepositoryTests {

    @Autowired
    private OrdersRepository ordersRepository;


    @Test
    public void testFindOrdersByUsername(){
        Assertions.assertEquals(1 , ordersRepository.findAllByUsername("bob").size());
    }

}
