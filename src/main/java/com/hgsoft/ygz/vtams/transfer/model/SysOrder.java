/**
 * Copyright &copy; 2012-2016 华软开发平台 All rights reserved.
 */
package com.hgsoft.ygz.vtams.transfer.model;

import org.hibernate.validator.constraints.Length;

import com.hgsoft.yfzx.webapp.base.entity.DataEntity;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

/**
 * 订单的增删改查Entity
 * @author 吴锡霖
 * @version 2016-11-09 00:12:14
 */
public class SysOrder extends DataEntity<SysOrder> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonView(DataTablesOutput.View.class)
	private String id;		// id
	@JsonView(DataTablesOutput.View.class)
	private String orderNo;		// 订单号
	@JsonView(DataTablesOutput.View.class)
	private String source;		// 来源
	@JsonView(DataTablesOutput.View.class)
	private String status;		// 状态
	@JsonView(DataTablesOutput.View.class)
	private Long money;		// 金额
	
	public SysOrder() {
	}

	@Length(min=1, max=50, message="id长度必须介于 1 和 50 之间")

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Length(min=0, max=50, message="订单号长度必须介于 0 和 50 之间")

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Length(min=0, max=50, message="来源长度必须介于 0 和 50 之间")

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	@Length(min=0, max=50, message="状态长度必须介于 0 和 50 之间")

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}
}