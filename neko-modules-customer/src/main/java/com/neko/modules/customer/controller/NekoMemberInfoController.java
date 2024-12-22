package com.neko.modules.customer.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import com.neko.modules.customer.entity.NekoMemberInfo;
import com.neko.modules.customer.service.INekoMemberInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 会员信息
 * @Author: jeecg-boot
 * @Date:   2024-12-13
 * @Version: V1.0
 */
@Api(tags="会员信息")
@RestController
@RequestMapping("/member/nekoMemberInfo")
@Slf4j
public class NekoMemberInfoController extends JeecgController<NekoMemberInfo, INekoMemberInfoService> {
	@Autowired
	private INekoMemberInfoService nekoMemberInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nekoMemberInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "会员信息-分页列表查询")
	@ApiOperation(value="会员信息-分页列表查询", notes="会员信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<NekoMemberInfo>> queryPageList(NekoMemberInfo nekoMemberInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<NekoMemberInfo> queryWrapper = QueryGenerator.initQueryWrapper(nekoMemberInfo, req.getParameterMap());
		Page<NekoMemberInfo> page = new Page<NekoMemberInfo>(pageNo, pageSize);
		IPage<NekoMemberInfo> pageList = nekoMemberInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param nekoMemberInfo
	 * @return
	 */
	@AutoLog(value = "会员信息-添加")
	@ApiOperation(value="会员信息-添加", notes="会员信息-添加")
	@RequiresPermissions("member:neko_member_info:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody NekoMemberInfo nekoMemberInfo) {
		nekoMemberInfoService.save(nekoMemberInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nekoMemberInfo
	 * @return
	 */
	@AutoLog(value = "会员信息-编辑")
	@ApiOperation(value="会员信息-编辑", notes="会员信息-编辑")
	@RequiresPermissions("member:neko_member_info:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody NekoMemberInfo nekoMemberInfo) {
		nekoMemberInfoService.updateById(nekoMemberInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "会员信息-通过id删除")
	@ApiOperation(value="会员信息-通过id删除", notes="会员信息-通过id删除")
	@RequiresPermissions("member:neko_member_info:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		nekoMemberInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "会员信息-批量删除")
	@ApiOperation(value="会员信息-批量删除", notes="会员信息-批量删除")
	@RequiresPermissions("member:neko_member_info:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nekoMemberInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "会员信息-通过id查询")
	@ApiOperation(value="会员信息-通过id查询", notes="会员信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<NekoMemberInfo> queryById(@RequestParam(name="id",required=true) String id) {
		NekoMemberInfo nekoMemberInfo = nekoMemberInfoService.getById(id);
		if(nekoMemberInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nekoMemberInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nekoMemberInfo
    */
    @RequiresPermissions("member:neko_member_info:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NekoMemberInfo nekoMemberInfo) {
        return super.exportXls(request, nekoMemberInfo, NekoMemberInfo.class, "会员信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("member:neko_member_info:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, NekoMemberInfo.class);
    }

}
