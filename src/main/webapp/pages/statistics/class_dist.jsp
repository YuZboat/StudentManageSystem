<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>班级成绩分析</title>
	<link href="../../css/search.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.stat-container { width: 900px; margin: 30px auto; }
		.page-title { text-align: center; font-size: 24px; font-weight: bold; color: #333; margin-bottom: 30px; }
		.search-box { text-align: center; margin-bottom: 30px; }
		.search-box select, .search-box input { padding: 8px; font-size: 14px; border-radius: 5px; border: 1px solid #ddd; }
		.search-box input { background-color: #667eea; color: white; cursor: pointer; border: none; }
		.search-box input:hover { background-color: #5a6fd6; }
		.stat-table { width: 100%; border-collapse: collapse; font-size: 14px; }
		.stat-table th, .stat-table td { border: 1px solid #ddd; padding: 12px; text-align: center; }
		.stat-table th { background-color: #667eea; color: white; font-weight: bold; }
		.stat-table tr:nth-child(even) { background-color: #f9f9f9; }
		.stat-table tr:hover { background-color: #f1f1f1; }
		.back-btn { display: block; width: 100px; height: 30px; line-height: 30px; text-align: center; background-color: #667eea; color: white; text-decoration: none; border-radius: 5px; margin: 20px auto; }
		.back-btn:hover { background-color: #5a6fd6; }
		.score-bar { height: 20px; border-radius: 10px; min-width: 10px; }
		.score-bar.excellent { background: linear-gradient(90deg, #4d96ff, #667eea); }
		.score-bar.good { background: linear-gradient(90deg, #6bcb77, #8ce99a); }
		.score-bar.medium { background: linear-gradient(90deg, #ffd93d, #ffe97f); }
		.score-bar.pass { background: linear-gradient(90deg, #ffa502, #ffc048); }
		.score-bar.fail { background: linear-gradient(90deg, #ff6b6b, #ffa8a8); }
		.chart-container { width: 150px; background-color: #eee; border-radius: 10px; padding: 2px; margin: 0 auto; }
	</style>
</head>
<body>
	<div class="stat-container">
		<div class="page-title">班级成绩分析</div>
		
		<div class="search-box">
			<form action="ScoreStatisticsServlet" method="post">
				<input type="hidden" name="type" value="class_dist" />
				<select name="cla_id">
					<option value="">全部班级</option>
					<c:forEach items="${classesList}" var="cls">
						<option value="${cls.id}" ${cls.id == selectedClaId ? 'selected' : ''}>${cls.name}</option>
					</c:forEach>
				</select>
				<input type="submit" value="查询" />
			</form>
		</div>

		<table class="stat-table">
			<tr>
				<th>课程名称</th>
				<th>平均分</th>
				<th>最高分</th>
				<th>最低分</th>
				<th>参考人数</th>
				<th>成绩分布</th>
			</tr>
			<c:forEach items="${classList}" var="item">
				<tr>
					<td>${item[0]}</td>
					<td>${item[1]}</td>
					<td>${item[2]}</td>
					<td>${item[3]}</td>
					<td>${item[4]}</td>
					<td>
						<div class="chart-container">
							<div class="score-bar" style="width: ${item[1]}%;"></div>
						</div>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty classList}">
				<tr>
					<td colspan="6" style="text-align: center;">暂无成绩数据</td>
				</tr>
			</c:if>
		</table>

		<a href="${pageContext.request.contextPath}/pages/statistics/index.jsp" class="back-btn">返回</a>
	</div>
</body>
</html>