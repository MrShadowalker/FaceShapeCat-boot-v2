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
import com.neko.modules.merchant.entity.NekoItemInfo;
import com.neko.modules.merchant.service.INekoItemInfoService;

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
 * @Description: 服务项目信息
 * @Author: jeecg-boot
 * @Date:   2024-12-22
 * @Version: V1.0
 */
@Api(tags="服务项目信息")
@RestController
@RequestMapping("/merchant/nekoItemInfo")
@Slf4j
public class NekoItemInfoController extends JeecgController<NekoItemInfo, INekoItemInfoService> {
	@Autowired
	private INekoItemInfoService nekoItemInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nekoItemInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "服务项目信息-分页列表查询")
	@ApiOperation(value="服务项目信息-分页列表查询", notes="服务项目信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<NekoItemInfo>> queryPageList(NekoItemInfo nekoItemInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<NekoItemInfo> queryWrapper = QueryGenerator.initQueryWrapper(nekoItemInfo, req.getParameterMap());
		Page<NekoItemInfo> page = new Page<NekoItemInfo>(pageNo, pageSize);
		IPage<NekoItemInfo> pageList = nekoItemInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param nekoItemInfo
	 * @return
	 */
	@AutoLog(value = "服务项目信息-添加")
	@ApiOperation(value="服务项目信息-添加", notes="服务项目信息-添加")
	@RequiresPermissions("merchant:neko_item_info:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody NekoItemInfo nekoItemInfo) {
		nekoItemInfoService.save(nekoItemInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nekoItemInfo
	 * @return
	 */
	@AutoLog(value = "服务项目信息-编辑")
	@ApiOperation(value="服务项目信息-编辑", notes="服务项目信息-编辑")
	@RequiresPermissions("merchant:neko_item_info:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody NekoItemInfo nekoItemInfo) {
		nekoItemInfoService.updateById(nekoItemInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "服务项目信息-通过id删除")
	@ApiOperation(value="服务项目信息-通过id删除", notes="服务项目信息-通过id删除")
	//@RequiresPermissions("merchant:neko_item_info:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		nekoItemInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "服务项目信息-批量删除")
	@ApiOperation(value="服务项目信息-批量删除", notes="服务项目信息-批量删除")
	//@RequiresPermissions("merchant:neko_item_info:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nekoItemInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "服务项目信息-通过id查询")
	@ApiOperation(value="服务项目信息-通过id查询", notes="服务项目信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<NekoItemInfo> queryById(@RequestParam(name="id",required=true) String id) {
		NekoItemInfo nekoItemInfo = nekoItemInfoService.getById(id);
		if(nekoItemInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nekoItemInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nekoItemInfo
    */
    //@RequiresPermissions("merchant:neko_item_info:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NekoItemInfo nekoItemInfo) {
        return super.exportXls(request, nekoItemInfo, NekoItemInfo.class, "服务项目信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("merchant:neko_item_info:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, NekoItemInfo.class);
    }

}
