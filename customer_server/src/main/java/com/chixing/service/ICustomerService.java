package com.chixing.service;

import java.util.Map;

public interface ICustomerService {
    public Map<String, Object> getById4OtherServer(Integer custId);
}
