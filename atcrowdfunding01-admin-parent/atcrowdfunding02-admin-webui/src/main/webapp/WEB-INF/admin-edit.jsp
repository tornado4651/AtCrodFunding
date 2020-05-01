<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="UTF-8">
<!-- 提取出navigator部分，单独包含进来 -->
<%@include file="/WEB-INF/include-head.jsp" %>

<body>

	<!-- 提取出navigator部分，单独包含进来 -->
	<%@include file="/WEB-INF/include-navigator.jsp" %>
	
	<div class="container-fluid">
		<div class="row">
			
			<!-- 提取出sidebar部分，单独包含进来 -->
			<%@include file="/WEB-INF/include-sidebar.jsp" %>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="/admin/to/main/page.html">首页</a></li>
					<li><a href="/admin/get/page.html">数据列表</a></li>
					<li class="active">更新</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
			  
			  	<font color="red">${requestScope.exception.message }</font>
			  	
				<form action="admin/update.html"  role="form">
					
					<!-- 隐藏域 -->
					<input type="hidden" name="id" value="${requestScope.admin.id }" >
				  	<input type="hidden" name="keyword" value="${param.keyword }">
				  	<input type="hidden" name="pageNum" value="${param.pageNum }">
				
				
				  <div class="form-group">
					<label for="exampleInputPassword1">登陆账号</label>
					<input type="text" class="form-control" name="loginAcct" id="exampleInputPassword1" value="${requestScope.admin.loginAcct }">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">用户名称</label>
					<input type="text" class="form-control" name="userName" id="exampleInputPassword1" value="${requestScope.admin.userName }">
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">邮箱地址</label>
					<input type="email" class="form-control" name="email"  id="exampleInputEmail1" value="${requestScope.admin.email }">
					<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxxxxxx@xxx.com</p>
				  </div>
				  <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 更新</button>
				  <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
			
		</div>
	</div>
</body>
</html>