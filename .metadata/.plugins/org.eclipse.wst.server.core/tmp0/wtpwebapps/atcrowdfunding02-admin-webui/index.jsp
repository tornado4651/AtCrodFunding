<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试主页</title>
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#btn1").click(function(){
			$.ajax({
				"url": "send/array/one.html",	// 请求目标资源的地址
				"type": "post",					// 请求方式
				"data": {						// 要发送的请求参数
					"array": [5,8,12]
				},
				"dataType": "text",					// 如何对待服务器端返回的数据
				"success": function(response) {		// 服务器端成功处理请求后调用的回调函数，response是响应体数据
					alert(response);
				},
				"error":function(response) {		// 服务器端处理请求失败后调用的回调函数，response是响应体数据
					alert(response);
				}
			});
		});
		
		
		$("#btn2").click(function(){	
			$.ajax({
				"url": "send/array/two.html",	// 请求目标资源的地址
				"type": "post",					// 请求方式
				"data": {						// 要发送的请求参数
					"array[0]": 5,
					"array[1]": 8,
					"array[2]": 11,
				},
				"dataType": "text",					// 如何对待服务器端返回的数据
				"success": function(response) {		// 服务器端成功处理请求后调用的回调函数，response是响应体数据
					alert(response);
				},
				"error":function(response) {		// 服务器端处理请求失败后调用的回调函数，response是响应体数据
					alert(response);
				}
			});
		});
		
		
		$("#btn3").click(function(){
			//首先定义数据，并转化成json格式
			var array = [5,8,12];
			var requestBody = JSON.stringify(array);
			
			$.ajax({
				"url": "send/array/three.html",
				"type": "post",
				"data": requestBody,
				"contentType": "application/json;charset=UTF-8",
				"dataType": "text",
				"success":function(response){
					alert(response);
				},
				"error": function(resonse){
					alert(reponse);
				}
			});
		});
		
		
		$("#btn4").click(function(){
			
			// 准备要发送的数据
			var student = {
				"stuId": 5,
				"stuName":"tom",
				"address": {
					"province": "广东",
					"city": "深圳",
					"street":"后瑞"
				},
				"subjectList": [
					{
						"subjectName": "JavaSE",
						"subjectScore": 100
					},{
						"subjectName": "SSM",
						"subjectScore": 99
					}
				],
				"map": {
					"k1":"v1",
					"k2":"v2"
				}
			};
			
			// 将JSON对象转换为JSON字符串
			var requestBody = JSON.stringify(student);
			
			// 发送Ajax请求
			$.ajax({
				"url":"send/compose/object.json",
				"type":"post",
				"data":requestBody,
				"contentType":"application/json;charset=UTF-8",
				"dataType":"json",
				"success":function(response){
					console.log(response);
				},
				"error":function(response){
					console.log(response);
				}
			});
		});//btn4 over
		
		
		$("#btn5").click(function(){
			layer.msg("layer的弹框");
		});//btn5 over
		
		
		
	});
</script>
</head>

<body>
	<h1>欢迎访问主页</h1>
	<br/>
	<a href="${pageContext.request.contextPath }/test/ssm.html">测试SSM整合环境</a>
	<br/>
	<button id="btn1">Test Jquery one：直接传入数组</button>
	<br/>
	<button id="btn2">Test Jquery two：用一个专门类的成员接收</button>
	<br/>
	<button id="btn3">Test Jquery three：使用json转化发送</button>
	<br/>
	<button id="btn4">Test Jquery four：接收复杂对象</button>
	
	<br/>
	<br/>
	<button id="btn5">点我测试layer弹框</button>
	
</body>
</html>