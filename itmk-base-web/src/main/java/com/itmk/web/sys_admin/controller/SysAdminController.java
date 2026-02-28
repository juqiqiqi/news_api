package com.itmk.web.sys_admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.status.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.sys_admin.entity.PageParm;
import com.itmk.web.sys_admin.entity.SysAdmin;
import com.itmk.web.sys_admin.service.SysAdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hc/sysAdmin")
public class SysAdminController {
    @Autowired
    private SysAdminService sysAdminService;
    //新增
    @PostMapping
    public ResultVo add(@RequestBody SysAdmin sysAdmin){
        if (sysAdminService.save(sysAdmin)){
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败！");
    }
    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysAdmin sysAdmin){
        if (sysAdminService.updateById(sysAdmin)){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败！");
    }
    //删除
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") long id){
        if (sysAdminService.removeById(id)){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }

    //列表
    @GetMapping("/getList")
    public ResultVo getList(PageParm parm){
        //构造查询条件
        QueryWrapper<SysAdmin> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getName()), SysAdmin::getName, parm.getName());
        //构造分页
        IPage<SysAdmin> page=new Page<>(parm.getCurrentPage(),parm.getPageSize());
        IPage<SysAdmin> list = sysAdminService.page(page,query);
        return ResultUtils.success("查询成功",list);
    }
}
