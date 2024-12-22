package com.neko.modules.customer.service;

import com.neko.modules.customer.entity.NekoCustomerCourseInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 顾客疗程信息表
 * @Author: jeecg-boot
 * @Date:   2024-12-22
 * @Version: V1.0
 */
public interface INekoCustomerCourseInfoService extends IService<NekoCustomerCourseInfo> {

    int countCustomerCourseByCustomerId(String customerId);

}
