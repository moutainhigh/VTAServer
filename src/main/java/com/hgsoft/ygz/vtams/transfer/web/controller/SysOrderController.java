/**
 * Copyright &copy; 2012-2016 华软开发平台 All rights reserved.
 */
package com.hgsoft.ygz.vtams.transfer.web.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import com.hgsoft.ygz.vtams.transfer.model.SysOrder;
import com.hgsoft.ygz.vtams.transfer.services.SysOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;

/**
 * SysOrderController
 * author 吴锡霖
 * version 2016-11-09 00:12:14
 */
@Controller
@RequestMapping(value = "/testorder/sysOrder")
public class SysOrderController {

    @Resource
    private SysOrderService sysOrderService;

    @RequiresPermissions("testorder:sysOrder:view")
    @GetMapping("/sysOrderList")
    public String sysOrderList() {
        return "testorder/sysOrder/sysOrderList";
    }

    @RequiresPermissions("testorder:sysOrder:view")
    @JsonView(DataTablesOutput.View.class)
    @ResponseBody
    @PostMapping("/table/sysOrderTable")
    public DataTablesOutput<SysOrder> findList(@Valid DataTablesInput input,
                                               SysOrder sysOrder) {
        return sysOrderService.findList(input, sysOrder);
    }

    @RequiresPermissions("testorder:sysOrder:create")
    @GetMapping("/sysOrderAdd")
    public String sysOrderAdd() {
        return "testorder/sysOrder/sysOrderAdd";
    }

    @RequiresPermissions("testorder:sysOrder:create")
    @PostMapping("/sysOrderAdd")
    public void sysOrderAdd(SysOrder sysOrder) {
        sysOrderService.saveSysOrder(sysOrder);
    }

    @RequiresPermissions("testorder:sysOrder:update")
    @GetMapping("/sysOrderEdit")
    public String sysOrderEdit(String id, Model model) {
        SysOrder sysOrder = sysOrderService.findSysOrderById(id);
        model.addAttribute("sysOrder", sysOrder);
        return "testorder/sysOrder/sysOrderEdit";
    }

    @RequiresPermissions("testorder:sysOrder:update")
    @PostMapping("/sysOrderEdit")
    public void edit(SysOrder sysOrder) {
        sysOrderService.updateSysOrder(sysOrder);
    }

    @RequiresPermissions("testorder:sysOrder:delete")
    @PostMapping("/sysOrderDel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByIds(String ids) {
        List<String> idList = convert(ids);
        sysOrderService.deleteSysOrderByIds(idList);
    }

    @RequiresPermissions("testorder:sysOrder:delete")
    @PostMapping("/sysOrderDelOne")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(String id) {
        sysOrderService.deleteSysOrderById(id);
    }

    private static List<String> convert(String ids) {
        String[] idArray = ids.split(",");
        List<String> idList = new ArrayList<>();
        for (int i = 0; i < idArray.length; i++) {
            idList.add(String.valueOf(idArray[i]));
        }
        return idList;
    }
}