package com.neko.modules.member.entity;

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
 * @Description: 会员信息
 * @Author: jeecg-boot
 * @Date:   2024-12-13
 * @Version: V1.0
 */
@Data
@TableName("neko_member_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="neko_member_info对象", description="会员信息")
public class NekoMemberInfo implements Serializable {
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
	/**顾客ID*/
	@Excel(name = "顾客ID", width = 15)
    @ApiModelProperty(value = "顾客ID")
    private java.lang.String customerId;
	/**会员类型*/
    @Excel(name = "会员类型", width = 15, dictTable = "sys_category", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "会员类型")
    private java.lang.String type;
	/**会员子类型*/
    @Excel(name = "会员子类型", width = 15, dictTable = "sys_category", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "会员子类型")
    private java.lang.String subType;
	/**会员等级*/
	@Excel(name = "会员等级", width = 15)
    @ApiModelProperty(value = "会员等级")
    private java.lang.Integer level;
	/**加入日期*/
	@Excel(name = "加入日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "加入日期")
    private java.util.Date joinDate;
	/**来源类型*/
	@Excel(name = "来源类型", width = 15)
    @ApiModelProperty(value = "来源类型")
    private java.lang.String sourceType;
	/**来源ID*/
	@Excel(name = "来源ID", width = 15)
    @ApiModelProperty(value = "来源ID")
    private java.lang.String sourceId;
	/**来源名称*/
	@Excel(name = "来源名称", width = 15)
    @ApiModelProperty(value = "来源名称")
    private java.lang.String sourceName;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}
