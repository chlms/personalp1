package com.campEZ.Project0.web;


import com.campEZ.Project0.orders.svc.OrdersSVC;
import com.campEZ.Project0.web.restReservation.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class RestOrdersController {

  private final OrdersSVC ordersSVC;

  @GetMapping("/{cnumber}/{area}")
  public RestResponse<Object> test(
      @PathVariable("cnumber") Integer cnumber,
      @PathVariable("area") Integer area
  ){
    RestResponse<Object> response = null;
    Integer integer = ordersSVC.campingAreaChange(cnumber, area);
    log.info("integer={}",integer);

    response = RestResponse.createRestResponse("00","성공", integer);

    return response;
  }
}
