package com.neko.modules.customer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neko.modules.customer.dto.request.AddMemberRechargeCardRequest;
import com.neko.modules.customer.dto.response.CustomerDetailVO;
import com.neko.modules.customer.dto.response.CustomerListPageVO;
import com.neko.modules.customer.entity.NekoCustomerCourseInfo;
import com.neko.modules.customer.entity.NekoCustomerInfo;
import com.neko.modules.customer.entity.NekoMemberInfo;
import com.neko.modules.customer.entity.NekoMemberRechargeCardInfo;
import com.neko.modules.customer.service.INekoCustomerCourseInfoService;
import com.neko.modules.customer.service.INekoCustomerInfoService;
import com.neko.modules.customer.service.INekoMemberInfoService;
import com.neko.modules.customer.service.INekoMemberRechargeCardInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    private INekoMemberInfoService nekoMemberInfoService;

    @Autowired
    private INekoMemberRechargeCardInfoService nekoMemberRechargeCardInfoService;

    @Autowired
    private INekoCustomerCourseInfoService nekoCustomerCourseInfoService;

    /**
     * 分页列表查询
     *
     * @param nekoCustomerInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "顾客相关信息-分页列表查询")
    @ApiOperation(value="顾客相关信息-分页列表查询", notes="顾客相关信息-分页列表查询")
    @GetMapping(value = "/queryPageList")
    public Result<IPage<CustomerListPageVO>> queryPageList(NekoCustomerInfo nekoCustomerInfo,
                                                           @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                           @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                           HttpServletRequest req) {
        // 自定义查询规则
        Map<String, QueryRuleEnum> customeRuleMap = new HashMap<>();
        // 自定义多选的查询规则为：LIKE_WITH_OR
        customeRuleMap.put("name", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("nickName", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("phoneNum", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("wechatNum", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("gender", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("source", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("subSource", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("memberId", QueryRuleEnum.LIKE_WITH_OR);
        QueryWrapper<NekoCustomerInfo> queryWrapper = QueryGenerator.initQueryWrapper(nekoCustomerInfo, req.getParameterMap(),customeRuleMap);
        Page<NekoCustomerInfo> page = new Page<NekoCustomerInfo>(pageNo, pageSize);
        IPage<NekoCustomerInfo> pageList = nekoCustomerInfoService.page(page, queryWrapper);
        IPage<CustomerListPageVO> customerList = pageList.convert(customerInfo -> {
            CustomerListPageVO customerListPageVO = new CustomerListPageVO();
            customerListPageVO.setCustomerInfo(customerInfo);
            customerListPageVO.setMemberRechargeCardCount(nekoMemberRechargeCardInfoService.countMemberCardByCustomerId(customerInfo.getId()));
            customerListPageVO.setCustomerCourseCount(nekoCustomerCourseInfoService.countCustomerCourseByCustomerId(customerInfo.getId()));
            return customerListPageVO;
        });
        return Result.OK(customerList);
    }

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

    /**
     * 新增会员充值卡信息
     */
    @ApiOperation(value="新增会员充值卡信息", notes="新增会员充值卡信息")
    @GetMapping(value = "/addMemberRechargeCard")
    public Result<CustomerDetailVO> addMemberRechargeCard(AddMemberRechargeCardRequest req) {
        // 根据顾客ID获取顾客信息
        NekoCustomerInfo nekoCustomerInfo = nekoCustomerInfoService.getById(req.getCustomerId());
        // 构造会员信息
        NekoMemberInfo memberInfo = buildMemberInfo(nekoCustomerInfo, req);
        // 保存会员信息
        nekoMemberInfoService.save(memberInfo);
        // 获取会员信息
        NekoMemberInfo nekoMemberInfo = nekoMemberInfoService.getByCustomerId(nekoCustomerInfo.getId());
        // 构造会员充值卡信息
        NekoMemberRechargeCardInfo memberRechargeCardInfo = buildMemberRechargeCardInfo(memberInfo, req);


    }

    private NekoMemberRechargeCardInfo buildMemberRechargeCardInfo(NekoMemberInfo memberInfo, AddMemberRechargeCardRequest req) {
        req.setMemberId(memberInfo.getId());
        return req;
    }

    private NekoMemberInfo buildMemberInfo(NekoCustomerInfo customer, AddMemberRechargeCardRequest rechargeCard) {
        NekoMemberInfo member = new NekoMemberInfo();
        member.setCreateBy(rechargeCard.getCreateBy());
        member.setCreateTime(rechargeCard.getCreateTime());
        member.setUpdateBy(rechargeCard.getUpdateBy());
        member.setUpdateTime(rechargeCard.getUpdateTime());
        member.setSysOrgCode(rechargeCard.getSysOrgCode());
        member.setTenantId(rechargeCard.getTenantId());
        member.setCustomerId(customer.getId());
        member.setJoinDate(rechargeCard.getCreateTime());
        if (rechargeCard.getRecommenderId().isBlank()) {
            member.setSourceType("店员推荐");
            member.setSourceId(rechargeCard.getEmployeeId());
        } else {
            member.setSourceType("会员推荐");
            member.setSourceId(rechargeCard.getRecommenderId());
        }
        return member;
    }

}
