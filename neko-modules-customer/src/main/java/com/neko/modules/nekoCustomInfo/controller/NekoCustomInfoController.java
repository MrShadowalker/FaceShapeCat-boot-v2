package com.neko.modules.nekoCustomInfo.controller;

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
import com.neko.modules.nekoCustomInfo.entity.NekoCustomInfo;
import com.neko.modules.nekoCustomInfo.service.INekoCustomInfoService;

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
 * @Description: 顾客信息
 * @Author: jeecg-boot
 * @Date:   2024-12-01
 * @Version: V1.0
 */
@Api(tags="顾客信息")
@RestController
@RequestMapping("/nekoCustomInfo/nekoCustomInfo")
@Slf4j
public class NekoCustomInfoController extends JeecgController<NekoCustomInfo, INekoCustomInfoService> {
	@Autowired
	private INekoCustomInfoService nekoCustomInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nekoCustomInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "顾客信息-分页列表查询")
	@ApiOperation(value="顾客信息-分页列表查询", notes="顾客信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<NekoCustomInfo>> queryPageList(NekoCustomInfo nekoCustomInfo,
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
        QueryWrapper<NekoCustomInfo> queryWrapper = QueryGenerator.initQueryWrapper(nekoCustomInfo, req.getParameterMap(),customeRuleMap);
		Page<NekoCustomInfo> page = new Page<NekoCustomInfo>(pageNo, pageSize);
		IPage<NekoCustomInfo> pageList = nekoCustomInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param nekoCustomInfo
	 * @return
	 */
	@AutoLog(value = "顾客信息-添加")
	@ApiOperation(value="顾客信息-添加", notes="顾客信息-添加")
	//@RequiresPermissions("nekoCustomInfo:neko_custom_info:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody NekoCustomInfo nekoCustomInfo) {
		nekoCustomInfoService.save(nekoCustomInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nekoCustomInfo
	 * @return
	 */
	@AutoLog(value = "顾客信息-编辑")
	@ApiOperation(value="顾客信息-编辑", notes="顾客信息-编辑")
	//@RequiresPermissions("nekoCustomInfo:neko_custom_info:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody NekoCustomInfo nekoCustomInfo) {
		nekoCustomInfoService.updateById(nekoCustomInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "顾客信息-通过id删除")
	@ApiOperation(value="顾客信息-通过id删除", notes="顾客信息-通过id删除")
	//@RequiresPermissions("nekoCustomInfo:neko_custom_info:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		nekoCustomInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "顾客信息-批量删除")
	@ApiOperation(value="顾客信息-批量删除", notes="顾客信息-批量删除")
	//@RequiresPermissions("nekoCustomInfo:neko_custom_info:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nekoCustomInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "顾客信息-通过id查询")
	@ApiOperation(value="顾客信息-通过id查询", notes="顾客信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<NekoCustomInfo> queryById(@RequestParam(name="id",required=true) String id) {
		NekoCustomInfo nekoCustomInfo = nekoCustomInfoService.getById(id);
		if(nekoCustomInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nekoCustomInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nekoCustomInfo
    */
    @RequiresPermissions("nekoCustomInfo:neko_custom_info:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NekoCustomInfo nekoCustomInfo) {
        return super.exportXls(request, nekoCustomInfo, NekoCustomInfo.class, "顾客信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("nekoCustomInfo:neko_custom_info:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, NekoCustomInfo.class);
    }

}
