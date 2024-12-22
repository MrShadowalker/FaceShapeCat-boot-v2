package com.neko.modules.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.neko.modules.customer.entity.NekoCustomerInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 顾客相关信息
 * @Author: jeecg-boot
 * @Date:   2024-12-13
 * @Version: V1.0
 */
public interface NekoCustomerInfoMapper extends BaseMapper<NekoCustomerInfo> {

    // queryCustomerIdByName
    public String queryCustomerIdByName(@Param("name") String name);
}
