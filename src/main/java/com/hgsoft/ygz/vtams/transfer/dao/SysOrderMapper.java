/**
 * Copyright &copy; 2012-2016 华软开发平台 All rights reserved.
 */
package com.hgsoft.ygz.vtams.transfer.dao;


import com.github.pagehelper.Page;
import com.hgsoft.yfzx.webapp.base.mapper.BaseMapper;
import com.hgsoft.yfzx.webapp.util.MyBatisRepository;
import com.hgsoft.ygz.vtams.transfer.model.SysOrder;

/**
 * 订单的增删改查Mapper接口
 *
 * @author 吴锡霖
 * @version 2016-11-09 00:12:14
 */
@MyBatisRepository
public interface SysOrderMapper extends BaseMapper<SysOrder, String> {

    Page<SysOrder> findList(SysOrder sysOrder);

}