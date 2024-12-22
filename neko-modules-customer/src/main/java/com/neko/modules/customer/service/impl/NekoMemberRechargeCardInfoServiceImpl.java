package com.neko.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neko.modules.customer.entity.NekoMemberRechargeCardInfo;
import com.neko.modules.customer.mapper.NekoMemberRechargeCardInfoMapper;
import com.neko.modules.customer.service.INekoMemberInfoService;
import com.neko.modules.customer.service.INekoMemberRechargeCardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 会员充值卡信息表
 * @Author: jeecg-boot
 * @Date:   2024-12-22
 * @Version: V1.0
 */
@Service
public class NekoMemberRechargeCardInfoServiceImpl extends ServiceImpl<NekoMemberRechargeCardInfoMapper, NekoMemberRechargeCardInfo> implements INekoMemberRechargeCardInfoService {

    @Override
    public Integer queryMemberCardCountByCustomerId(String customerId) {
        QueryWrapper<NekoMemberRechargeCardInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id",customerId);
        return Math.toIntExact(this.count(queryWrapper));
    }

}
