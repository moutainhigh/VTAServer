<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="main">
    <div class="page-header">
        <h3>
            <span>添加订单</span>
        </h3>
    </div>

    <form class="form-horizontal form-bordered" action="../testorder/sysOrder/sysOrderAdd" method="post">
        <div class="form-body">
        
			<div class="form-group ">
                <label class="col-sm-3 control-label">
                    
                    订单号
                </label>
                <div class="col-sm-9">
			<input type="text" name="orderNo"  maxlength="50"
                           class="form-control "
                           placeholder="订单号" />
				</div>
			</div>
			<div class="form-group ">
                <label class="col-sm-3 control-label">
                    
                    来源
                </label>
                <div class="col-sm-9">
			<input type="text" name="source"  maxlength="50"
                           class="form-control "
                           placeholder="来源" />
				</div>
			</div>
			<div class="form-group ">
                <label class="col-sm-3 control-label">
                    
                    状态
                </label>
                <div class="col-sm-9">
			<input type="text" name="status"  maxlength="50"
                           class="form-control "
                           placeholder="状态" />
				</div>
			</div>
			<div class="form-group last">
                <label class="col-sm-3 control-label">
                    
                    金额
                </label>
                <div class="col-sm-9">
			<input type="text" name="money" 
                           class="form-control  digits"
                           placeholder="金额" />
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
        $('form').validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: form.method,
                    url: form.action,
                    success: function (data) {
                        alert('添加成功');
                        apiUtil.closeCurrentTab();
                    }
                });
            }
        });
    });
</script>