package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.Util.TokenUtil;
import com.chixing.config.ServerResponse;
import com.chixing.mapper.Customermapper;
import com.chixing.pojo.Customer;
import com.chixing.pojo.CustomerTokenDTO;
import com.chixing.service.ICustomerService;
import org.springframework.stereotype.Service;

/**
 * @author 小番茄
 * @date 2022/10/1114:10
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    public final Customermapper customermapper;
    public CustomerServiceImpl(Customermapper customermapper) {
        this.customermapper = customermapper;
    }

    @Override
    public ServerResponse login(Customer customer) {
        //1. DB 验证用户名与密码
        QueryWrapper<Customer> wrapper =  new QueryWrapper<>();
        wrapper.eq("cust_name",customer.getCustName());
        wrapper.eq("cust_password",customer.getCustPassword());

        Customer loginCustomer = customermapper.selectOne(wrapper);
        System.out.println("查询到的登录账户：" + loginCustomer);

        if(loginCustomer !=null) {

            CustomerTokenDTO customerTokenDTO = new CustomerTokenDTO();
            customerTokenDTO.setCustId(loginCustomer.getCustId());
            customerTokenDTO.setCustName(loginCustomer.getCustName());

            //2. JWT创建token
            String token = TokenUtil.sign(customerTokenDTO);
            TokenUtil.parseToken(token);

            System.out.println("service token:" + token);
            return ServerResponse.success("登录成功", token);
        }else {
            return ServerResponse.fail("登录失败",loginCustomer);
        }
    }
}
