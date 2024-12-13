package com.neko.modules.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.neko.modules.member.entity.NekoMemberInfo;
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
