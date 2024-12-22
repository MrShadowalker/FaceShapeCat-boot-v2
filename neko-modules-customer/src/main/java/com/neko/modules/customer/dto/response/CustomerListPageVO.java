package com.neko.modules.customer.dto.response;

import com.neko.modules.customer.entity.NekoCustomerInfo;
import lombok.Data;

/**
 * @author Shadowalker
 */
@Data
public class CustomerListPageVO extends NekoCustomerInfo {
    private int memberRechargeCardCount;
    private int customerCourseCount;
}
