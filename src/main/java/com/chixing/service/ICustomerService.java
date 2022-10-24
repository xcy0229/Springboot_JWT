package com.chixing.service;

import com.chixing.config.ServerResponse;
import com.chixing.pojo.Customer;

/**
 * @author 小番茄
 * @date 2022/10/1114:11
 */
public interface ICustomerService {
    ServerResponse login(Customer customer);
}
