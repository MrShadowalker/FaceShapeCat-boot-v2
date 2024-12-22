package com.neko.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neko.modules.customer.entity.NekoCustomerCourseInfo;
import com.neko.modules.customer.mapper.NekoCustomerCourseInfoMapper;
import com.neko.modules.customer.service.INekoCustomerCourseInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 顾客疗程信息表
 * @Author: jeecg-boot
 * @Date:   2024-12-22
 * @Version: V1.0
 */
@Service
public class NekoCustomerCourseInfoServiceImpl extends ServiceImpl<NekoCustomerCourseInfoMapper, NekoCustomerCourseInfo> implements INekoCustomerCourseInfoService {

    @Override
    public int countCustomerCourseByCustomerId(String customerId) {
        QueryWrapper<NekoCustomerCourseInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id",customerId);
        return Math.toIntExact(this.count(queryWrapper));
    }
}
