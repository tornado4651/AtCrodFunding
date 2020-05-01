<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="UTF-8">
<!-- 提取出navigator部分，单独包含进来 -->
<%@include file="/WEB-INF/include-head.jsp" %>

<script type="text/javascript">
	$(function(){
		// 实现角色选择框的添加功能
		// ":eq(0)"表示选择页面上的第一个选择框
		// "eq(1)"表示选择页面上的第一个选择框
		// ">"表示选择子元素
		// “:selected”表示选择“被选中”的option
		// appendTo()能够将jQuery对象追加到指定位置
		$("#toRightBtn").click(function() {
			$("select:eq(0)>option:selected").appendTo("select:eq(1)");
		});
		
		// 实现角色选择框的删除功能
		$("#toLeftBtn").click(function(){
			$("select:eq(1) > option:selected").appendTo("select:eq(0)");
		});
		
		// 设置提交按钮中所有option选中，避免bug
		$("#submitBtn").click(function(){
			$("select:eq(1) > option").prop("selected", "selected");
		});
	});
</script>

<body>

	<!-- 提取出navigator部分，单独包含进来 -->
	<%@include file="/WEB-INF/include-navigator.jsp" %>
	
	<div class="container-fluid">
		<div class="row">
			
			<!-- 提取出sidebar部分，单独包含进来 -->
			<%@include file="/WEB-INF/include-sidebar.jsp" %>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">分配角色</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form  action="assign/do/role/assign.html" method="post" role="form" class="form-inline">
				  
				  <input type="hidden" name="adminId" value="${param.adminId }"/>
				  <input type="hidden" name="pageNum" value="${param.pageNum }"/>
				  <input type="hidden" name="keyword" value="${param.keyword }"/>
				  
				  <div class="form-group">
					<label for="exampleInputPassword1">未分配角色列表</label><br>
					<select  
						class="form-control" multiple="multiple" size="10" 
						style="width:100px;overflow-y:auto;">
                        <c:forEach items="${requestScope.unAssignedRoleList }" var="role">
                        	<option value="${role.id }">${role.name }</option>
                        </c:forEach>
                    </select>
				  </div>
				  <div class="form-group">
                        <ul>
                            <li  id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                            <br>
                            <li  id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"/>
                        </ul>
				  </div>
				  <div class="form-group" style="margin-left:40px;">
					<label for="exampleInputPassword1">已分配角色列表</label><br>
					<select name="roleIdList" 
						class="form-control" multiple="multiple" size="10" 
						style="width:100px;overflow-y:auto;">
                        <c:forEach items="${requestScope.assignedRoleList }" var="role">
                        	<option value="${role.id }">${role.name }</option>
                        </c:forEach>
                    </select>
				  </div>
				  
				  <button id="submitBtn" type="submit" style="width:150px" class="btn btn-sm btn-success btn-block">保存</button>
				  
				</form>
			  </div>
			</div>
        </div>
			
		</div>
	</div>
</body>
</html>