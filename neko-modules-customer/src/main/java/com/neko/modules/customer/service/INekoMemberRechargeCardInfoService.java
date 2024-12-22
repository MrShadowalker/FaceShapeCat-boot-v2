package com.neko.modules.customer.service;

import com.neko.modules.customer.entity.NekoMemberRechargeCardInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 会员充值卡信息表
 * @Author: jeecg-boot
 * @Date:   2024-12-22
 * @Version: V1.0
 */
public interface INekoMemberRechargeCardInfoService extends IService<NekoMemberRechargeCardInfo> {

    Integer countMemberCardByCustomerId(String customerId);

}
