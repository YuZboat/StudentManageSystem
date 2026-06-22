<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>成绩统计分析</title>
	<link href="../../css/search.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.stat-menu {
			width: 600px;
			margin: 50px auto;
			text-align: center;
		}
		.stat-item {
			display: inline-block;
			width: 180px;
			height: 120px;
			margin: 20px;
			background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
			border-radius: 10px;
			text-align: center;
			line-height: 120px;
			color: white;
			font-size: 16px;
			font-weight: bold;
			cursor: pointer;
			box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
			transition: transform 0.3s, box-shadow 0.3s;
		}
		.stat-item:hover {
			transform: translateY(-5px);
			box-shadow: 0 8px 25px rgba(102, 126, 234, 0.6);
		}
		.stat-item a {
			color: white;
			text-decoration: none;
			display: block;
			width: 100%;
			height: 100%;
		}
		.page-title {
			text-align: center;
			font-size: 24px;
			font-weight: bold;
			color: #333;
			margin-bottom: 30px;
		}
	</style>
</head>
<body>
	<div class="page-title">成绩统计分析系统</div>
	<div class="stat-menu">
		<div class="stat-item">
			<a href="${pageContext.request.contextPath}/ScoreStatisticsServlet?type=course_avg">课程平均分统计</a>
		</div>
		<div class="stat-item">
			<a href="${pageContext.request.contextPath}/ScoreStatisticsServlet?type=score_dist">分数段人数统计</a>
		</div>
		<div class="stat-item">
			<a href="${pageContext.request.contextPath}/ScoreStatisticsServlet?type=class_dist">班级成绩分析</a>
		</div>
	</div>
	<a href="${pageContext.request.contextPath}/pages/index.jsp" class="back-btn" style="display: block; width: 100px; height: 30px; line-height: 30px; text-align: center; background-color: #667eea; color: white; text-decoration: none; border-radius: 5px; margin: 30px auto;">返回主页</a>
</div>
</body>
</html>