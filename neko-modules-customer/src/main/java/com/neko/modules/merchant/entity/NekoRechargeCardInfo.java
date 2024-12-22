package com.neko.modules.merchant.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 充值卡元信息
 * @Author: jeecg-boot
 * @Date:   2024-12-22
 * @Version: V1.0
 */
@Data
@TableName("neko_recharge_card_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="neko_recharge_card_info对象", description="充值卡元信息")
public class NekoRechargeCardInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**删除标志位*/
	@Excel(name = "删除标志位", width = 15)
    @ApiModelProperty(value = "删除标志位")
    @TableLogic
    private java.lang.Integer delFlag;
	/**租户ID*/
	@Excel(name = "租户ID", width = 15)
    @ApiModelProperty(value = "租户ID")
    private java.lang.Integer tenantId;
	/**充值卡名*/
	@Excel(name = "充值卡名", width = 15)
    @ApiModelProperty(value = "充值卡名")
    private java.lang.String cardName;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
    private java.lang.String cardNum;
	/**卡片样式链接*/
	@Excel(name = "卡片样式链接", width = 15)
    @ApiModelProperty(value = "卡片样式链接")
    private java.lang.String cardIconUrl;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.String status;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
    private java.lang.String type;
	/**子类型*/
	@Excel(name = "子类型", width = 15)
    @ApiModelProperty(value = "子类型")
    private java.lang.String subType;
	/**来源*/
	@Excel(name = "来源", width = 15)
    @ApiModelProperty(value = "来源")
    private java.lang.String source;
	/**子来源*/
	@Excel(name = "子来源", width = 15)
    @ApiModelProperty(value = "子来源")
    private java.lang.String subSource;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
    private java.util.Date startDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
    private java.util.Date endDate;
	/**储值金额*/
	@Excel(name = "储值金额", width = 15)
    @ApiModelProperty(value = "储值金额")
    private java.math.BigDecimal rechargeAmount;
	/**赠金金额*/
	@Excel(name = "赠金金额", width = 15)
    @ApiModelProperty(value = "赠金金额")
    private java.math.BigDecimal bonusAmount;
	/**服务折扣，单位：万分比*/
	@Excel(name = "服务折扣，单位：万分比", width = 15)
    @ApiModelProperty(value = "服务折扣，单位：万分比")
    private java.math.BigDecimal serviceDiscountRate;
	/**商品折扣，单位：万分比*/
	@Excel(name = "商品折扣，单位：万分比", width = 15)
    @ApiModelProperty(value = "商品折扣，单位：万分比")
    private java.math.BigDecimal productDiscountRate;
	/**疗程折扣，单位：万分比*/
	@Excel(name = "疗程折扣，单位：万分比", width = 15)
    @ApiModelProperty(value = "疗程折扣，单位：万分比")
    private java.math.BigDecimal courseDiscountRate;
}
