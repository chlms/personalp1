package com.campEZ.Project0.web;


import com.campEZ.Project0.entity.Orders;
import com.campEZ.Project0.orders.svc.OrdersSVC;
import com.campEZ.Project0.web.form.orders.OrdersForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

  private final OrdersSVC ordersSVC;


  //예약화면
  @GetMapping("/{cnumber}") //캠핑장상세페이지에서 예약페이지로 캠핑장번호 가져오기
  public String ordersForm(
      //data-cnumber받기
      @PathVariable("cnumber") Integer cnumber, //캠핑장상세페이지에서 예약페이지로 캠핑장번호 가져오기
      Model model,
      HttpSession session //세션 가져오기
      ){
    //세션 가져오기
    LoginMembers loginMembers = (LoginMembers)session.getAttribute(SessionConst.LOGIN_MEMBER);
    OrdersForm ordersForm = new OrdersForm();
    //아이디 가져오기
    ordersForm.setMid(loginMembers.getMid());

    //캠핑장상세페이지에서 예약페이지로 캠핑장번호 가져오기
    ordersForm.setCnumber(cnumber);

    //캠핑장 번호로 구역정보 가져오기
    List<Integer> reserAreas = ordersSVC.campingFindByCnumber(cnumber);
    log.info("reserAreas={}",reserAreas.size());

    //캠핑테이블에서 캠핑장 번호로 소개글 가져오기
    String campingCtext = ordersSVC.campingCtext(cnumber);
    log.info("campingCtext={}",campingCtext);

    //구역정보 모델에 저장하기
    model.addAttribute("reserAreas",reserAreas);

    //캠핑테이블에서 캠핑장 번호로 소개글 가져와 모델에 저장하기
    model.addAttribute("campingCtext",campingCtext);

    model.addAttribute("ordersForm", ordersForm);

    log.info("model={}",model);
    return "orders/orders";
  }



  //예약처리
  @PostMapping("/{cnumber}")
  public String order(
      @Valid @ModelAttribute("ordersForm") OrdersForm ordersForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ){
    //등록
    Orders orders = new Orders();
    orders.setCnumber(ordersForm.getCnumber());
    orders.setArea(ordersForm.getArea());
    orders.setMid(ordersForm.getMid());
    orders.setPhone(ordersForm.getPhone());
    orders.setHeadcount(ordersForm.getHeadcount());
    orders.setCheckin(ordersForm.getCheckin());
    orders.setCheckout(ordersForm.getCheckout());

    Orders savedOnumber = ordersSVC.order(orders);
    int onumber = Integer.valueOf(savedOnumber.getOnumber());
    redirectAttributes.addAttribute("id", onumber);

    log.info("redirectAttributes={}",redirectAttributes);

    return "orders/ordersCheck";
  }

}
