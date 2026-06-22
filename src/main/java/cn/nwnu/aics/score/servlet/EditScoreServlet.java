package cn.nwnu.aics.score.servlet;

import cn.nwnu.aics.impl.ScoreImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Score;

public class EditScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 准备更新学生成绩信息
		ScoreImpl scoreImpl = new ScoreImpl();
		HttpSession session = request.getSession();
		Score score = scoreImpl.query("sco_id", request.getParameter("sco_id"))
				.get(0);
		session.setAttribute("score", score);
		session.setAttribute("student", score.getStudent());
		session.setAttribute("subject", score.getSubject());
		response.sendRedirect("pages/update_score.jsp");
	}
}
