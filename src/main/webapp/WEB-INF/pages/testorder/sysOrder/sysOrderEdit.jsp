<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="main">
    <div class="page-header">
        <h3>
            <span>编辑订单</span>
        </h3>
    </div>

    <form class="form-horizontal form-bordered changeListen" action="../testorder/sysOrder/sysOrderEdit" method="post">
        <div class="form-body">
        	<input type="hidden" name="id" value="${sysOrder.id}">
			<div class="form-group ">
                <label class="col-sm-3 control-label">
                    
                    订单号
                </label>
                <div class="col-sm-9">
			<input type="text" name="orderNo"  maxlength="50"
                           class="form-control "
                           placeholder="订单号" value="${sysOrder.orderNo}" />
				</div>
			</div>
			<div class="form-group ">
                <label class="col-sm-3 control-label">
                    
                    来源
                </label>
                <div class="col-sm-9">
			<input type="text" name="source"  maxlength="50"
                           class="form-control "
                           placeholder="来源" value="${sysOrder.source}" />
				</div>
			</div>
			<div class="form-group ">
                <label class="col-sm-3 control-label">
                    
                    状态
                </label>
                <div class="col-sm-9">
			<input type="text" name="status"  maxlength="50"
                           class="form-control "
                           placeholder="状态" value="${sysOrder.status}" />
				</div>
			</div>
			<div class="form-group last">
                <label class="col-sm-3 control-label">
                    
                    金额
                </label>
                <div class="col-sm-9">
			<input type="text" name="money" 
                           class="form-control  digits"
                           placeholder="金额" value="${sysOrder.money}" />
				</div>
			</div>
        </div>
        <div class="form-actions">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn btn-primary pull-left margin-right-10">保存</button>
                            <button type="button"  onclick="apiUtil.closeCurrentTab();" class="btn btn-default pull-left">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    require(['apiUtil', 'bootbox', 'validate_full'], function (apiUtil, bootbox) {
        window.bootbox = bootbox;
        window.apiUtil = apiUtil;
        apiUtil.formIsChangeListener();
        
        $('form').validate({
            submitHandler: function (form) {
                //提交前判断，如果表单没有改变则不提交
                //if (!form.data("changed")) 直接使用form会导致错误
                if (!$('form').data("changed")) {
                    return;
                }
                $(form).ajaxSubmit({
                    type: form.method,
                    url: form.action,
                    success: function (data) {
                        alert("修改成功!");
                        apiUtil.closeCurrentTab();
                    }
                });
            }
        });
    });
</script>