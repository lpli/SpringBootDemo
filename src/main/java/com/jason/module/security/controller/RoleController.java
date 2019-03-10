package com.jason.module.security.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jason.common.enums.ResponseCode;
import com.jason.common.vo.JsonResponse;
import com.jason.module.security.entity.Role;
import com.jason.module.security.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lpli
 * @since 2019-01-05
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public JsonResponse create(@RequestBody Role role) {
        role.setCreator(this.getToken().getUsername());
        role.setCreateTime(new Date());
        roleService.save(role);
        return JsonResponse.buildSuccess();
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody Role role) {
        roleService.updateById(role);
        return JsonResponse.buildSuccess();
    }

    @DeleteMapping("/{id}/delete")
    public JsonResponse delete(@PathVariable("id") Long id) {
        roleService.remove(new QueryWrapper<Role>().eq("id", id));
        return JsonResponse.buildSuccess();
    }

    @GetMapping("/pageList")
    public JsonResponse<Page<Role>> pageList(long pageSize, long pageNo) {
        JsonResponse<Page<Role>> json = new JsonResponse<>();
        Page<Role> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNo);
        IPage<Role> roleIPage = roleService.page(page, new QueryWrapper<Role>().eq("creator", this.getToken().getUsername()));
        page.setRecords(roleIPage.getRecords());
        page.setTotal(roleIPage.getTotal());
        json.setCode(ResponseCode.SUCCESS.getCode());
        json.setData(page);
        return json;
    }

    @GetMapping("/check")
    public JsonResponse check(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if(role.getId()!=null){
            queryWrapper = queryWrapper.ne("id", role.getId());
        }
        if (StringUtils.isNotEmpty(role.getName())) {
            int count = roleService.count(queryWrapper.eq("name", role.getName()));
            if (count > 0) {
                return JsonResponse.buildFail("角色名已存在");
            }
        }
        if(StringUtils.isNotEmpty(role.getCode())){
            int count = roleService.count(queryWrapper.eq("code", role.getCode()));
            if (count > 0) {
                return JsonResponse.buildFail("编码已存在");
            }
        }
        return JsonResponse.buildSuccess();
    }
}

