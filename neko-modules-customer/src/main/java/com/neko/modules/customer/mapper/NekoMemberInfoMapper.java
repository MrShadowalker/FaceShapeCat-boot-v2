package com.neko.modules.customer.mapper;

import com.neko.modules.customer.entity.NekoMemberInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 会员信息
 * @Author: jeecg-boot
 * @Date:   2024-12-13
 * @Version: V1.0
 */
public interface NekoMemberInfoMapper extends BaseMapper<NekoMemberInfo> {

    Integer queryMemberCardCount(String customerId);
}
