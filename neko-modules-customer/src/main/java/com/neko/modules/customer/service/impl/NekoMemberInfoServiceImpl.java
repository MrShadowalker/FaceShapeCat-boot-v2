package com.neko.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neko.modules.customer.entity.NekoMemberInfo;
import com.neko.modules.customer.mapper.NekoMemberInfoMapper;
import com.neko.modules.customer.service.INekoMemberInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 会员信息
 * @Author: jeecg-boot
 * @Date:   2024-12-13
 * @Version: V1.0
 */
@Service
public class NekoMemberInfoServiceImpl extends ServiceImpl<NekoMemberInfoMapper, NekoMemberInfo> implements INekoMemberInfoService {

    @Override
    public NekoMemberInfo getByCustomerId(String customerId) {
        QueryWrapper<NekoMemberInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id", customerId);
        return this.getOne(queryWrapper);
    }
}
