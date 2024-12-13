package com.neko.modules.customer.service.impl;

import com.neko.modules.customer.entity.NekoCustomerInfo;
import com.neko.modules.customer.mapper.NekoCustomerInfoMapper;
import com.neko.modules.customer.service.INekoCustomerInfoService;
import com.neko.modules.member.mapper.NekoMemberInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 顾客相关信息
 * @Author: jeecg-boot
 * @Date:   2024-12-13
 * @Version: V1.0
 */
@Service
public class NekoCustomerInfoServiceImpl extends ServiceImpl<NekoCustomerInfoMapper, NekoCustomerInfo> implements INekoCustomerInfoService {

    @Autowired
    private NekoMemberInfoMapper nekoMemberInfoMapper;

    @Override
    public String queryCustomerIdByName(String name) {
        return baseMapper.queryCustomerIdByName(name);
    }

    @Override
    public Integer queryMemberCardCount(String customerId) {
        return nekoMemberInfoMapper.queryMemberCardCount(customerId);
    }
}
