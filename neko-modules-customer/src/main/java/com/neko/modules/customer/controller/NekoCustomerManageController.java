package com.neko.modules.customer.controller;

import com.neko.modules.customer.dto.response.CustomerDetailVO;
import com.neko.modules.customer.entity.NekoCustomerCourseInfo;
import com.neko.modules.customer.entity.NekoMemberRechargeCardInfo;
import com.neko.modules.customer.service.INekoCustomerCourseInfoService;
import com.neko.modules.customer.service.INekoCustomerInfoService;
import com.neko.modules.customer.service.INekoMemberRechargeCardInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shadowalker
 */
@Api(tags="顾客管理")
@RestController
@RequestMapping("/customer/nekoCustomerManage")
@Slf4j
public class NekoCustomerManageController {

    @Autowired
    private INekoCustomerInfoService nekoCustomerInfoService;

    @Autowired
    private INekoMemberRechargeCardInfoService nekoMemberRechargeCardInfoService;

    @Autowired
    private INekoCustomerCourseInfoService nekoCustomerCourseInfoService;

    /**
     * 查询顾客详情：全部关联信息
     */
    @ApiOperation(value="查询顾客详情：全部关联信息", notes="查询顾客详情：全部关联信息")
    @GetMapping(value = "/queryCustomerDetail")
    public Result<CustomerDetailVO> queryCustomerDetail(@RequestParam(name="customerId",required=true) String customerId) {
        CustomerDetailVO customerDetail = new CustomerDetailVO();
        customerDetail.setCustomerInfo(nekoCustomerInfoService.getById(customerId));
        customerDetail.setMemberRechargeCardCount(nekoMemberRechargeCardInfoService.countMemberCardByCustomerId(customerId));
        customerDetail.setCustomerCourseCount(nekoCustomerCourseInfoService.countCustomerCourseByCustomerId(customerId));
        Map<String, Object> memberRechargeCardInfoMap = new HashMap<>();
        memberRechargeCardInfoMap.put("customerId", customerId);
        List<NekoMemberRechargeCardInfo> memberRechargeCardInfoList = nekoMemberRechargeCardInfoService.listByMap(memberRechargeCardInfoMap);
        customerDetail.setMemberRechargeCardInfoList(memberRechargeCardInfoList);
        Map<String, Object> customerCourseInfoMap = new HashMap<>();
        customerCourseInfoMap.put("customerId", customerId);
        List<NekoCustomerCourseInfo> customerCourseInfoList = nekoCustomerCourseInfoService.listByMap(customerCourseInfoMap);
        customerDetail.setCustomerCourseInfoList(customerCourseInfoList);
        return Result.OK(customerDetail);
    }

}
