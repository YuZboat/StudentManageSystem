<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>课程平均分统计</title>
	<link href="../../css/search.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.stat-container { width: 800px; margin: 30px auto; }
		.page-title { text-align: center; font-size: 24px; font-weight: bold; color: #333; margin-bottom: 30px; }
		.stat-table { width: 100%; border-collapse: collapse; font-size: 14px; }
		.stat-table th, .stat-table td { border: 1px solid #ddd; padding: 12px; text-align: center; }
		.stat-table th { background-color: #667eea; color: white; font-weight: bold; }
		.stat-table tr:nth-child(even) { background-color: #f9f9f9; }
		.stat-table tr:hover { background-color: #f1f1f1; }
		.back-btn { display: block; width: 100px; height: 30px; line-height: 30px; text-align: center; background-color: #667eea; color: white; text-decoration: none; border-radius: 5px; margin: 20px auto; }
		.back-btn:hover { background-color: #5a6fd6; }
		.chart-container { width: 150px; background-color: #eee; border-radius: 10px; padding: 2px; margin: 0 auto; }
		.chart-bar { height: 20px; background: linear-gradient(90deg, #667eea 0%, #764ba2 100%); border-radius: 10px; min-width: 5px; }
	</style>
</head>
<body>
	<div class="stat-container">
		<div class="page-title">课程平均分统计</div>
		<table class="stat-table">
			<tr>
				<th>课程名称</th>
				<th>平均分</th>
				<th>学生人数</th>
				<th>成绩分布图</th>
			</tr>
			<c:forEach items="${avgList}" var="item">
				<tr>
					<td>${item[0]}</td>
					<td>${item[1]}</td>
					<td>${item[2]}</td>
					<td>
						<div class="chart-container">
							<div class="chart-bar" style="width: ${item[1]}%;"></div>
						</div>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty avgList}">
				<tr>
					<td colspan="4" style="text-align: center;">暂无成绩数据</td>
				</tr>
			</c:if>
		</table>
		<a href="${pageContext.request.contextPath}/pages/statistics/index.jsp" class="back-btn">返回</a>
	</div>
</body>
</html>