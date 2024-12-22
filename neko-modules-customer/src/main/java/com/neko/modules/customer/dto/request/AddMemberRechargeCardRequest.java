package com.neko.modules.customer.dto.request;

import lombok.Data;

/**
 * @author Shadowalker
 */
@Data
public class AddMemberRechargeCardRequest {
    private String rechargeCardMetaId; // 选择哪张充值卡模板
    private String cardName;
    private String rechargeAmount;
    private String bonusAmount;
    private
}
