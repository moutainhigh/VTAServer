/**
 * Copyright &copy; 2012-2016 华软开发平台 All rights reserved.
 */
package com.hgsoft.ygz.vtams.transfer.services;

import com.hgsoft.ygz.vtams.transfer.model.SysOrder;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

/**
 * 订单的增删改查Service
 * @author 吴锡霖
 * @version 2016-11-09 00:12:14
 */
public interface SysOrderService {

	void saveSysOrder(SysOrder sysOrder);

	void updateSysOrder(SysOrder sysOrder);

	DataTablesOutput<SysOrder> findList(DataTablesInput input,
			SysOrder sysOrder);

    SysOrder findSysOrderById(String id);

    void deleteSysOrderByIds(List<String> idList);

    void deleteSysOrderById(String id);
	
}