package cn.nwnu.aics.teacher.servlet;

import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Operator;
import cn.nwnu.aics.entity.Teacher;

public class InfoTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TeacherImpl teacherImpl = new TeacherImpl();
		HttpSession session = request.getSession();
		Operator operator = (Operator) session.getAttribute("log_operator");
		Teacher teacher = teacherImpl.query("ope_id", operator.getId() + "")
				.get(0);
		session.setAttribute("teacher_me", teacher);
		response.sendRedirect("pages/info_teacher.jsp");
	}
}
