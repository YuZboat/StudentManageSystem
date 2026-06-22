package cn.nwnu.aics.score.servlet;

import cn.nwnu.aics.impl.ScoreImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.entity.Score;

public class UpdateScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 更新学生成绩
		ScoreImpl scoreImpl = new ScoreImpl();
		Score score = scoreImpl.query("sco_id", request.getParameter("sco_id"))
				.get(0);
		score.setDaily(Double.parseDouble(request.getParameter("daily")));
		score.setExam(Double.parseDouble(request.getParameter("exam")));
		score.setCount(score.getDaily() + score.getExam());
		scoreImpl.update(score);
		response.sendRedirect("/Student/pages/search_score.jsp");
	}
}
