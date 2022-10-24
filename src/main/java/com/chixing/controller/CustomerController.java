package com.chixing.controller;

import com.chixing.Util.TokenUtil;
import com.chixing.config.ServerResponse;
import com.chixing.pojo.Customer;
import com.chixing.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 小番茄
 * @date 2022/10/1114:23
 */
@Controller
@RequestMapping("/counmt")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }
    /**
     *  登录
     */
    @PostMapping("/login")
    @ResponseBody
    public ServerResponse login(Customer customer, HttpServletRequest request, HttpServletResponse response){
        ServerResponse result = customerService.login(customer);
        System.out.println("controller login response:" + result);
        if(result.getResultCode() == 200){
            System.out.println("customer controller 登录成功");
        }
        return result;
    }
    @GetMapping("/test1")
    @ResponseBody
    public String test1(HttpServletRequest request){
        //从请求头部获得token
        String token = request.getHeader("token");
        System.out.println(token);
        //解析token,获得登录用户信息
        Integer customerId = TokenUtil.parseToken(token).getCustId();
        System.out.println("test1 customer Id = " + customerId);
        return "test1 get token ok";
    }
}
