package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.Customer;
import com.chixing.mapper.CustomerMapper;
import com.chixing.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xue
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerMapper mapper;
    public CustomerServiceImpl(CustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Map<String, Object> getById4OtherServer(Integer custId) {
        Map<String,Object> result = new HashMap<>();
        //掉用构造方法
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("cust_id","cust_name");
        //.eq 表示等于
        queryWrapper.eq("cust_id",custId);

        Customer customer = mapper.selectOne(queryWrapper);
        //对外服务，用户服务只显示 用户id 与昵称
        result.put("userId",customer.getCustId());
        result.put("useraName",customer.getCustName());
        return result;
    }
}
