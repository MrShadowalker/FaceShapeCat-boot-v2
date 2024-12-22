package com.neko.modules.customer.entity;

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
 * @Description: 顾客疗程信息表
 * @Author: jeecg-boot
 * @Date:   2024-12-22
 * @Version: V1.0
 */
@Data
@TableName("neko_customer_course_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="neko_customer_course_info对象", description="顾客疗程信息表")
public class NekoCustomerCourseInfo implements Serializable {
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
	/**疗程ID*/
	@Excel(name = "疗程ID", width = 15)
    @ApiModelProperty(value = "疗程ID")
    private java.lang.String courseId;
	/**疗程名称*/
	@Excel(name = "疗程名称", width = 15)
    @ApiModelProperty(value = "疗程名称")
    private java.lang.String courseName;
	/**顾客ID*/
	@Excel(name = "顾客ID", width = 15)
    @ApiModelProperty(value = "顾客ID")
    private java.lang.String customerId;
	/**会员ID*/
	@Excel(name = "会员ID", width = 15)
    @ApiModelProperty(value = "会员ID")
    private java.lang.String memberId;
	/**店员ID*/
	@Excel(name = "店员ID", width = 15)
    @ApiModelProperty(value = "店员ID")
    private java.lang.String employeeId;
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
	/**渠道*/
	@Excel(name = "渠道", width = 15)
    @ApiModelProperty(value = "渠道")
    private java.lang.String source;
	/**子渠道*/
	@Excel(name = "子渠道", width = 15)
    @ApiModelProperty(value = "子渠道")
    private java.lang.String subSource;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
    private java.util.Date startDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
    private java.util.Date endDate;
	/**有效期*/
	@Excel(name = "有效期", width = 15)
    @ApiModelProperty(value = "有效期")
    private java.lang.Integer effectiveDay;
	/**项目ID*/
	@Excel(name = "项目ID", width = 15)
    @ApiModelProperty(value = "项目ID")
    private java.lang.String itemId;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
    private java.lang.String itemName;
	/**项目次数*/
	@Excel(name = "项目次数", width = 15)
    @ApiModelProperty(value = "项目次数")
    private java.lang.Integer itemTimes;
	/**剩余次数*/
	@Excel(name = "剩余次数", width = 15)
    @ApiModelProperty(value = "剩余次数")
    private java.lang.Integer remainTimes;
	/**购买价格，单位：分*/
	@Excel(name = "购买价格，单位：分", width = 15)
    @ApiModelProperty(value = "购买价格，单位：分")
    private java.math.BigDecimal purchasePrice;
	/**购买折扣，单位：万分比*/
	@Excel(name = "购买折扣，单位：万分比", width = 15)
    @ApiModelProperty(value = "购买折扣，单位：万分比")
    private java.math.BigDecimal purchaseDiscountRate;
}
