package com.campEZ.Project0.orders.dao;

import com.campEZ.Project0.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class OrdersDAOImplTest {

  @Autowired
  private OrdersDAO ordersDAO;

  @Test
  void order() {
    Orders orders = new Orders();
    orders.setCnumber(1);
    orders.setArea(2);
    orders.setMid("test126");
    orders.setPhone("000-0000");
    orders.setHeadcount(2);
    orders.setCheckin("2월 10일");
    orders.setCheckout("3월 10일");

    ordersDAO.order(orders);
  }

  @Test
  void test1(){
    Integer integer = ordersDAO.campingAreaChange(1, 2);
    log.info("integer={}",integer);
    Assertions.assertThat(integer).isEqualTo(5);

  }
  @Test
  void test2(){
    String s = ordersDAO.campingCtext(1);
    log.info("s={}",s);
  }

}