<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List, java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>分数段人数统计</title>
	<link href="../../css/search.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.stat-container { width: 800px; margin: 30px auto; }
		.page-title { text-align: center; font-size: 24px; font-weight: bold; color: #333; margin-bottom: 30px; }
		.search-box { text-align: center; margin-bottom: 30px; }
		.search-box select, .search-box input { padding: 8px; font-size: 14px; border-radius: 5px; border: 1px solid #ddd; }
		.search-box input { background-color: #667eea; color: white; cursor: pointer; border: none; }
		.search-box input:hover { background-color: #5a6fd6; }
		.stat-card { display: inline-block; width: 140px; margin: 15px; text-align: center; border-radius: 10px; padding: 20px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
		.stat-card.fail { background-color: #ff6b6b; }
		.stat-card.sixties { background-color: #ffa502; }
		.stat-card.seventies { background-color: #ffd93d; }
		.stat-card.eighties { background-color: #6bcb77; }
		.stat-card.nineties { background-color: #4d96ff; }
		.stat-card .label { color: white; font-size: 12px; margin-bottom: 10px; }
		.stat-card .count { color: white; font-size: 28px; font-weight: bold; }
		.stat-card .percent { color: rgba(255,255,255,0.8); font-size: 12px; }
		.back-btn { display: block; width: 100px; height: 30px; line-height: 30px; text-align: center; background-color: #667eea; color: white; text-decoration: none; border-radius: 5px; margin: 30px auto; }
		.back-btn:hover { background-color: #5a6fd6; }
		.bar-chart { width: 100%; max-width: 600px; margin: 30px auto; }
		.bar-row { display: flex; align-items: center; margin: 10px 0; }
		.bar-label { width: 100px; font-size: 12px; text-align: right; padding-right: 10px; }
		.bar-container { flex: 1; background-color: #f0f0f0; height: 30px; border-radius: 5px; overflow: hidden; }
		.bar-fill { height: 100%; border-radius: 5px; }
		.bar-fill.fail { background-color: #ff6b6b; }
		.bar-fill.sixties { background-color: #ffa502; }
		.bar-fill.seventies { background-color: #ffd93d; }
		.bar-fill.eighties { background-color: #6bcb77; }
		.bar-fill.nineties { background-color: #4d96ff; }
		.bar-value { width: 100px; text-align: left; padding-left: 10px; font-weight: bold; }
	</style>
</head>
<body>
	<div class="stat-container">
		<div class="page-title">分数段人数统计</div>
		
		<div class="search-box">
			<form action="ScoreStatisticsServlet" method="post">
				<input type="hidden" name="type" value="score_dist" />
				<select name="sub_id">
					<option value="">全部课程</option>
					<c:forEach items="${subjectList}" var="sub">
						<option value="${sub.id}" ${sub.id == selectedSubId ? 'selected' : ''}>${sub.name}</option>
					</c:forEach>
				</select>
				<input type="submit" value="查询" />
			</form>
		</div>

		<c:if test="${not empty distList}">
			<%
				List<Object[]> distList = (List<Object[]>)request.getAttribute("distList");
				if (distList != null && !distList.isEmpty()) {
					Object[] data = distList.get(0);
					int fail = ((Number)data[0]).intValue();
					int sixties = ((Number)data[1]).intValue();
					int seventies = ((Number)data[2]).intValue();
					int eighties = ((Number)data[3]).intValue();
					int nineties = ((Number)data[4]).intValue();
					int total = ((Number)data[5]).intValue();
					
					double failPct = total > 0 ? (fail * 100.0 / total) : 0;
					double sixtiesPct = total > 0 ? (sixties * 100.0 / total) : 0;
					double seventiesPct = total > 0 ? (seventies * 100.0 / total) : 0;
					double eightiesPct = total > 0 ? (eighties * 100.0 / total) : 0;
					double ninetiesPct = total > 0 ? (nineties * 100.0 / total) : 0;
			%>
			
			<div style="text-align: center; margin-bottom: 20px;">
				<span style="font-size: 16px; color: #666;">总人数：<%= total %>人</span>
			</div>

			<div style="text-align: center;">
				<div class="stat-card fail">
					<div class="label">不及格(0-59)</div>
					<div class="count"><%= fail %></div>
					<div class="percent"><%= String.format("%.1f", failPct) %>%</div>
				</div>
				<div class="stat-card sixties">
					<div class="label">及格(60-69)</div>
					<div class="count"><%= sixties %></div>
					<div class="percent"><%= String.format("%.1f", sixtiesPct) %>%</div>
				</div>
				<div class="stat-card seventies">
					<div class="label">中等(70-79)</div>
					<div class="count"><%= seventies %></div>
					<div class="percent"><%= String.format("%.1f", seventiesPct) %>%</div>
				</div>
				<div class="stat-card eighties">
					<div class="label">良好(80-89)</div>
					<div class="count"><%= eighties %></div>
					<div class="percent"><%= String.format("%.1f", eightiesPct) %>%</div>
				</div>
				<div class="stat-card nineties">
					<div class="label">优秀(90-100)</div>
					<div class="count"><%= nineties %></div>
					<div class="percent"><%= String.format("%.1f", ninetiesPct) %>%</div>
				</div>
			</div>

			<div class="bar-chart">
				<div class="bar-row">
					<div class="bar-label">不及格</div>
					<div class="bar-container">
						<div class="bar-fill fail" style="width: <%= Math.max(failPct, 2) %>%;"></div>
					</div>
					<div class="bar-value"><%= fail %>人 (<%= String.format("%.1f", failPct) %>%)</div>
				</div>
				<div class="bar-row">
					<div class="bar-label">及格</div>
					<div class="bar-container">
						<div class="bar-fill sixties" style="width: <%= Math.max(sixtiesPct, 2) %>%;"></div>
					</div>
					<div class="bar-value"><%= sixties %>人 (<%= String.format("%.1f", sixtiesPct) %>%)</div>
				</div>
				<div class="bar-row">
					<div class="bar-label">中等</div>
					<div class="bar-container">
						<div class="bar-fill seventies" style="width: <%= Math.max(seventiesPct, 2) %>%;"></div>
					</div>
					<div class="bar-value"><%= seventies %>人 (<%= String.format("%.1f", seventiesPct) %>%)</div>
				</div>
				<div class="bar-row">
					<div class="bar-label">良好</div>
					<div class="bar-container">
						<div class="bar-fill eighties" style="width: <%= Math.max(eightiesPct, 2) %>%;"></div>
					</div>
					<div class="bar-value"><%= eighties %>人 (<%= String.format("%.1f", eightiesPct) %>%)</div>
				</div>
				<div class="bar-row">
					<div class="bar-label">优秀</div>
					<div class="bar-container">
						<div class="bar-fill nineties" style="width: <%= Math.max(ninetiesPct, 2) %>%;"></div>
					</div>
					<div class="bar-value"><%= nineties %>人 (<%= String.format("%.1f", ninetiesPct) %>%)</div>
				</div>
			</div>
			<% } %>
		</c:if>

		<c:if test="${empty distList}">
			<div style="text-align: center; padding: 50px; color: #999;">暂无成绩数据</div>
		</c:if>

		<a href="${pageContext.request.contextPath}/pages/statistics/index.jsp" class="back-btn">返回</a>
	</div>
</body>
</html>