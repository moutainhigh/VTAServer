<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="main">
    <div class="wrapper">
        <div class="condition-header">
            <span>订单查询</span>
            <button id="search" type="button"
                    class="btn btn-primary pull-right">搜索
            </button>
        </div>
		<form class="form-horizontal search saveForm">
                        <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-4">订单号</label>
                            <div class="col-md-8">
                                <input name="orderNo" type="text"
                                       class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-4">来源</label>
                            <div class="col-md-8">
                                <input name="source" type="text"
                                       class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-4">创建时间</label>
                            <div class="col-md-8">
                                <input name="createTime" type="text"
                                       class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-4">状态</label>
                            <div class="col-md-8">
                                <input name="status" type="text"
                                       class="form-control"/>
                            </div>
                        </div>
                    </div>
                        </div>
                        <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-4">金额</label>
                            <div class="col-md-8">
                                <input name="money" type="text"
                                       class="form-control"/>
                            </div>
                        </div>
                    </div>
                        </div>
		</form>
    </div>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse toolbar-collapse">
                <ul class="nav navbar-nav">
					<shiro:hasPermission name="testorder:sysOrder:create">
						<li has-permission="'testorder:sysOrder:create'">
							<a href="#/testorder/sysOrder/sysOrderAdd">
								<span class="glyphicon glyphicon-plus glyphicon-active"></span>
								添加
							</a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="testorder:sysOrder:delete">
						<li has-permission="'testorder:sysOrder:delete'">
							<a id="del" href="javascript:;" title="删除">
								<span class="glyphicon glyphicon-trash"></span>
								删除
							</a>
						</li>
                    </shiro:hasPermission>
                </ul>
            </div>
        </div>
    </nav>
    <table class="table table-hover table-condensed" id="dataTable">
        <thead>
        <tr>
            <th></th>
            <th>订单号</th>
            <th>来源</th>
            <th>创建时间</th>
            <th>状态</th>
            <th>金额</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

</div>
<script type="text/javascript">
    require(['CustomDateTime', 'custom-datatable', 'moment', 'datatable-select', 'datatable-colReorder', 'WdatePicker','validate_full'], function () {
        $('#dataTable').HgDataTable({    //“dataTable”是需要显示的table的对应的id
            checkbox: true,            //是否显示复选框
            isAjax: true,
            searchFormSelector: 'form.search', //搜索条件表单的选择器,当你指定此设置时，表单中的搜索条件也会作为参数发送到服务器端
            searchBtnSelector: '#search',
            ajax: {                         //从一个ajax数据源读取数据给表格内容
                url: '../testorder/sysOrder/table/sysOrderTable'   //获取数据的url，默认是post提交
            },
            order: [[1, 'asc']], //默认第二列升序排序，0代表第一列依此类推，最后一列可用-1表示，倒数第二列可用-2表示，依此类推。
            columns: [ //列数据定义
                {          
                    "data": "id",       //此列显示返回的json数据中的id，但是渲染的时候返回“”,所以什么都不显示，显示的是复选框
                    "render": function (data, type, full) {
                        return "";
                    }
                },
                {
                    data: 'orderNo' //显示json数据中个orderNo
                },             
                {
                    data: 'source' //显示json数据中个orderNo
                },             
                {
                    data: 'createTime',
                    "render": function (data, type, full) {     //data：为当前的data(createTime),full是此行的所有数据，
                        if (data != null) {                     // 对数据进行显示渲染的时候可以格式化数据等（决定如何显示该数据
                            return moment(data).format('YYYY-MM-DD HH:mm:ss');
                        } else {
                            return "";
                        }
                    }
                },         
                {
                    data: 'status' //显示json数据中个orderNo
                },             
                {
                    data: 'money' //显示json数据中个orderNo
                },             
                {
                    orderable: false,
                    "data": "id",
                    "render": function (data, type, full) {
                        return '<div class="text-center operation" data-id="' + data +'">'
                            <shiro:hasPermission name="testorder:sysOrder:update">
                                + '<a href="#/testorder/sysOrder/sysOrderEdit?id=' + data + '" class="pointer edit" >编辑</a>'
                            </shiro:hasPermission>
                            <shiro:hasPermission name="testorder:sysOrder:delete">
                                + '<a href="javascript:;" class="pointer delOne">删除</a>'
                            </shiro:hasPermission>
                                + '</div>';
                    }
                }
            ]
        });
    <shiro:hasPermission name="testorder:sysOrder:delete">
        $('#del').off("click");
        $('#del').click(function () {
            var table = $('#dataTable').DataTable();
            var checked = table.rows('.selected').data();
            if (checked.length == 0) {
                alert('请至少选择一条记录！');
                return;
            }
        
            var ids = [];
            for (var i = 0; i < checked.length; i++) {
                ids.push(checked[i].id);
            }
        
            confirm('是否要删除？',
                function() {
                    $.post('../testorder/sysOrder/sysOrderDel', {ids: ids.join(',')}, function (data) {
                        alert('删除成功!');
                        var table = $('#dataTable').DataTable();
                        table.draw();
                    })
                });
        
        
        });
    
        $(document).off("click", '.delOne');
        $(document).on('click', '.delOne', function () {
            $(this).parent().parent().siblings().click();
            var id = $(this).parent().attr("data-id");
        
            confirm('是否要删除？' ,function(){
                $.post('../testorder/sysOrder/sysOrderDelOne', {id: id}, function (data) {
                    alert('删除成功!');
                    var table = $('#dataTable').DataTable();
                    table.draw();
                });
            });
        
        
        });
    </shiro:hasPermission>
        
    });
</script>