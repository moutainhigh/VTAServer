/**
 * Copyright &copy; 2012-2016 华软开发平台 All rights reserved.
 */
package com.hgsoft.ygz.vtams.transfer.services.impl;

import java.util.List;

import com.hgsoft.yfzx.webapp.base.service.CrudService;
import com.hgsoft.ygz.vtams.transfer.dao.SysOrderMapper;
import com.hgsoft.ygz.vtams.transfer.model.SysOrder;
import com.hgsoft.ygz.vtams.transfer.services.SysOrderService;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.hgsoft.yfzx.webapp.util.IdWorkerInit;
import com.hgsoft.yfzx.webapp.util.PageHelper;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

/**
 * 订单的增删改查ServiceImpl
 * @author 吴锡霖
 * @version 2016-11-09 00:12:14
 */
@Service
public class SysOrderServiceImpl extends CrudService<SysOrderMapper, SysOrder, String> implements SysOrderService {

	@Override
    public void saveSysOrder(SysOrder sysOrder) {
		sysOrder.setId(IdWorkerInit.generateIdStr());
        insertSelective(sysOrder);
    }

    @Override
    public void updateSysOrder(SysOrder sysOrder) {
        updateByPrimaryKeySelective(sysOrder);
    }

	@Override
	public DataTablesOutput<SysOrder> findList(DataTablesInput input,
                                               SysOrder sysOrder) {
		PageHelper.startPage(input);
        return mapper.findList(sysOrder)
                .toTablesOutput(input);
    }

    @Override
    public SysOrder findSysOrderById(String id) {
        return selectByPrimaryKey(id);
    }

    @Override
    public void deleteSysOrderByIds(List<String> idList) {
        deleteByPrimaryKeys(idList);
    }
    
    @Override
    public void deleteSysOrderById(String id) {
        deleteByPrimaryKey(id);
    }

}