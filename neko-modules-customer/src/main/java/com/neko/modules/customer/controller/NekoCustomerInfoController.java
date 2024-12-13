package com.neko.modules.customer.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import com.neko.modules.customer.entity.NekoCustomerInfo;
import com.neko.modules.customer.service.INekoCustomerInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 顾客相关信息
 * @Author: jeecg-boot
 * @Date:   2024-12-13
 * @Version: V1.0
 */
@Api(tags="顾客相关信息")
@RestController
@RequestMapping("/customer/nekoCustomerInfo")
@Slf4j
public class NekoCustomerInfoController extends JeecgController<NekoCustomerInfo, INekoCustomerInfoService> {
	@Autowired
	private INekoCustomerInfoService nekoCustomerInfoService;
	
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
	@GetMapping(value = "/list")
	public Result<IPage<NekoCustomerInfo>> queryPageList(NekoCustomerInfo nekoCustomerInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        // 自定义查询规则
        Map<String, QueryRuleEnum> customeRuleMap = new HashMap<>();
        // 自定义多选的查询规则为：LIKE_WITH_OR
        customeRuleMap.put("gender", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("source", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("subSource", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("memberId", QueryRuleEnum.LIKE_WITH_OR);
        QueryWrapper<NekoCustomerInfo> queryWrapper = QueryGenerator.initQueryWrapper(nekoCustomerInfo, req.getParameterMap(),customeRuleMap);
		Page<NekoCustomerInfo> page = new Page<NekoCustomerInfo>(pageNo, pageSize);
		IPage<NekoCustomerInfo> pageList = nekoCustomerInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param nekoCustomerInfo
	 * @return
	 */
	@AutoLog(value = "顾客相关信息-添加")
	@ApiOperation(value="顾客相关信息-添加", notes="顾客相关信息-添加")
	//@RequiresPermissions("customer:neko_customer_info:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody NekoCustomerInfo nekoCustomerInfo) {
		nekoCustomerInfoService.save(nekoCustomerInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nekoCustomerInfo
	 * @return
	 */
	@AutoLog(value = "顾客相关信息-编辑")
	@ApiOperation(value="顾客相关信息-编辑", notes="顾客相关信息-编辑")
	//@RequiresPermissions("customer:neko_customer_info:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody NekoCustomerInfo nekoCustomerInfo) {
		nekoCustomerInfoService.updateById(nekoCustomerInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "顾客相关信息-通过id删除")
	@ApiOperation(value="顾客相关信息-通过id删除", notes="顾客相关信息-通过id删除")
	//@RequiresPermissions("customer:neko_customer_info:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		nekoCustomerInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "顾客相关信息-批量删除")
	@ApiOperation(value="顾客相关信息-批量删除", notes="顾客相关信息-批量删除")
	//@RequiresPermissions("customer:neko_customer_info:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nekoCustomerInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "顾客相关信息-通过id查询")
	@ApiOperation(value="顾客相关信息-通过id查询", notes="顾客相关信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<NekoCustomerInfo> queryById(@RequestParam(name="id",required=true) String id) {
		NekoCustomerInfo nekoCustomerInfo = nekoCustomerInfoService.getById(id);
		if(nekoCustomerInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nekoCustomerInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nekoCustomerInfo
    */
    @RequiresPermissions("customer:neko_customer_info:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NekoCustomerInfo nekoCustomerInfo) {
        return super.exportXls(request, nekoCustomerInfo, NekoCustomerInfo.class, "顾客相关信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("customer:neko_customer_info:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, NekoCustomerInfo.class);
    }

}
