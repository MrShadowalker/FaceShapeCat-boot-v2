package com.neko.modules.customer.controller;

import com.neko.modules.customer.dto.response.CustomerDetailVO;
import com.neko.modules.customer.service.INekoCustomerInfoService;
import com.neko.modules.customer.service.INekoMemberRechargeCardInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 查顾客当前有几张会员卡
     */
    @ApiOperation(value="通过顾客ID查询会员卡数量", notes="通过顾客ID查询会员卡数量")
    @GetMapping(value = "/queryMemberCardCountByCustomerId")
    public Result<Integer> queryMemberCardCountByCustomerId(@RequestParam(name="customerId",required=true) String customerId) {
        Integer count = nekoMemberRechargeCardInfoService.queryMemberCardCountByCustomerId(customerId);
        return Result.OK(count);
    }

    /**
     * 查询顾客详情：全部关联信息
     */
    @ApiOperation(value="查询顾客详情：全部关联信息", notes="查询顾客详情：全部关联信息")
    @GetMapping(value = "/queryCustomerDetail")
    public Result<CustomerDetailVO> queryCustomerDetail(@RequestParam(name="customerId",required=true) String customerId) {
        CustomerDetailVO customerDetail = new CustomerDetailVO();
        // 顾客信息
        customerDetail.setCustomerInfo(nekoCustomerInfoService.getById(customerId));
        customerDetail.setMemberRechargeCardInfoList(nekoMemberRechargeCardInfoService.listByMap(null));
        return Result.OK(customerDetail);
    }

}
