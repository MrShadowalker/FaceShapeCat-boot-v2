package com.neko.modules.customer.service;

import com.neko.modules.customer.entity.NekoMemberInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 会员信息
 * @Author: jeecg-boot
 * @Date:   2024-12-13
 * @Version: V1.0
 */
public interface INekoMemberInfoService extends IService<NekoMemberInfo> {

    NekoMemberInfo getByCustomerId(String customerId);

}
