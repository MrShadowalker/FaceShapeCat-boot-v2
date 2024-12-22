package com.neko.modules.customer.dto.response;

import com.neko.modules.customer.entity.NekoCustomerCourseInfo;
import com.neko.modules.customer.entity.NekoCustomerInfo;
import com.neko.modules.customer.entity.NekoMemberRechargeCardInfo;
import lombok.Data;

import java.util.List;

/**
 * @author Shadowalker
 */
@Data
public class CustomerDetailVO {
    private NekoCustomerInfo customerInfo;
    private int memberRechargeCardCount;
    private int customerCourseCount;
    private List<NekoMemberRechargeCardInfo> memberRechargeCardInfoList;
    private List<NekoCustomerCourseInfo> customerCourseInfoList;
}
