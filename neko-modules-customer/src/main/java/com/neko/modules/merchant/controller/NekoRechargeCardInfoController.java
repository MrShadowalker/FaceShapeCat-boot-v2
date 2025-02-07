package com.neko.modules.merchant.controller;

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
import com.neko.modules.merchant.entity.NekoRechargeCardInfo;
import com.neko.modules.merchant.service.INekoRechargeCardInfoService;

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
 * @Description: 充值卡元信息
 * @Author: jeecg-boot
 * @Date:   2024-12-22
 * @Version: V1.0
 */
@Api(tags="充值卡元信息")
@RestController
@RequestMapping("/merchant/nekoRechargeCardInfo")
@Slf4j
public class NekoRechargeCardInfoController extends JeecgController<NekoRechargeCardInfo, INekoRechargeCardInfoService> {
	@Autowired
	private INekoRechargeCardInfoService nekoRechargeCardInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nekoRechargeCardInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "充值卡元信息-分页列表查询")
	@ApiOperation(value="充值卡元信息-分页列表查询", notes="充值卡元信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<NekoRechargeCardInfo>> queryPageList(NekoRechargeCardInfo nekoRechargeCardInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<NekoRechargeCardInfo> queryWrapper = QueryGenerator.initQueryWrapper(nekoRechargeCardInfo, req.getParameterMap());
		Page<NekoRechargeCardInfo> page = new Page<NekoRechargeCardInfo>(pageNo, pageSize);
		IPage<NekoRechargeCardInfo> pageList = nekoRechargeCardInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param nekoRechargeCardInfo
	 * @return
	 */
	@AutoLog(value = "充值卡元信息-添加")
	@ApiOperation(value="充值卡元信息-添加", notes="充值卡元信息-添加")
	//@RequiresPermissions("merchant:neko_recharge_card_info:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody NekoRechargeCardInfo nekoRechargeCardInfo) {
		nekoRechargeCardInfoService.save(nekoRechargeCardInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nekoRechargeCardInfo
	 * @return
	 */
	@AutoLog(value = "充值卡元信息-编辑")
	@ApiOperation(value="充值卡元信息-编辑", notes="充值卡元信息-编辑")
	//@RequiresPermissions("merchant:neko_recharge_card_info:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody NekoRechargeCardInfo nekoRechargeCardInfo) {
		nekoRechargeCardInfoService.updateById(nekoRechargeCardInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "充值卡元信息-通过id删除")
	@ApiOperation(value="充值卡元信息-通过id删除", notes="充值卡元信息-通过id删除")
	@RequiresPermissions("merchant:neko_recharge_card_info:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		nekoRechargeCardInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "充值卡元信息-批量删除")
	@ApiOperation(value="充值卡元信息-批量删除", notes="充值卡元信息-批量删除")
	@RequiresPermissions("merchant:neko_recharge_card_info:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nekoRechargeCardInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "充值卡元信息-通过id查询")
	@ApiOperation(value="充值卡元信息-通过id查询", notes="充值卡元信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<NekoRechargeCardInfo> queryById(@RequestParam(name="id",required=true) String id) {
		NekoRechargeCardInfo nekoRechargeCardInfo = nekoRechargeCardInfoService.getById(id);
		if(nekoRechargeCardInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nekoRechargeCardInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nekoRechargeCardInfo
    */
    //@RequiresPermissions("merchant:neko_recharge_card_info:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NekoRechargeCardInfo nekoRechargeCardInfo) {
        return super.exportXls(request, nekoRechargeCardInfo, NekoRechargeCardInfo.class, "充值卡元信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("merchant:neko_recharge_card_info:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, NekoRechargeCardInfo.class);
    }

}
