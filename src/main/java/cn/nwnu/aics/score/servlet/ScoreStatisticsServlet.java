package cn.nwnu.aics.score.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.impl.ScoreImpl;
import cn.nwnu.aics.impl.SubjectImpl;
import cn.nwnu.aics.impl.ClassesImpl;
import cn.nwnu.aics.entity.Subject;
import cn.nwnu.aics.entity.Classes;

public class ScoreStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreImpl scoreImpl = new ScoreImpl();
	private SubjectImpl subjectImpl = new SubjectImpl();
	private ClassesImpl classesImpl = new ClassesImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if ("course_avg".equals(type)) {
			List<Object[]> avgList = scoreImpl.getCourseAvgScore();
			request.setAttribute("avgList", avgList);
			request.getRequestDispatcher("/pages/statistics/course_avg.jsp").forward(request, response);
		} else if ("score_dist".equals(type)) {
			String subId = request.getParameter("sub_id");
			List<Object[]> distList = scoreImpl.getScoreDistribution(subId);
			List<Subject> subjectList = subjectImpl.query("all", "");
			request.setAttribute("distList", distList);
			request.setAttribute("subjectList", subjectList);
			request.setAttribute("selectedSubId", subId);
			request.getRequestDispatcher("/pages/statistics/score_dist.jsp").forward(request, response);
		} else if ("class_dist".equals(type)) {
			String claId = request.getParameter("cla_id");
			List<Object[]> classList = scoreImpl.getClassScoreDistribution(claId);
			List<Classes> classesList = classesImpl.query("all", "");
			request.setAttribute("classList", classList);
			request.setAttribute("classesList", classesList);
			request.setAttribute("selectedClaId", claId);
			request.getRequestDispatcher("/pages/statistics/class_dist.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/pages/statistics/index.jsp").forward(request, response);
		}
	}
}