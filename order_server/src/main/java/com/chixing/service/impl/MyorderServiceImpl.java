package com.chixing.service.impl;

import com.chixing.entity.Myorder;
import com.chixing.mapper.MyorderMapper;
import com.chixing.service.CustomerClient;
import com.chixing.service.IMyorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chixing.service.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangXu
 * @since 2022-10-17
 */
@Service
public class MyorderServiceImpl extends ServiceImpl<MyorderMapper, Myorder> implements IMyorderService {
    private final MyorderMapper mapper;
    private final CustomerClient customerClient;
    private final ProductClient  productClient;

    public MyorderServiceImpl(MyorderMapper mapper, CustomerClient customerClient, ProductClient productClient) {
        this.mapper = mapper;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    @Override
    public Map getOrderDetail(Integer orderId) {
        Map<String,Object> map = new HashMap<>();

        Myorder myorder = mapper.selectById(orderId);  // order mapper
        Map customerMap = customerClient.getCustomerById(myorder.getCustId());  // openFeign call customer  server
        Map productMap = productClient.getProductById(myorder.getProId()); // openFeign call product server


        map.put("order",myorder);
        map.putAll(customerMap);
        map.putAll(productMap);
        return map;
    }
}
