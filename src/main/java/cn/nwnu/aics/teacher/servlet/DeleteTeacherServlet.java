package cn.nwnu.aics.teacher.servlet;

import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TeacherImpl teacherImpl = new TeacherImpl();
		teacherImpl.delete(teacherImpl.query("tec_id",
				request.getParameter("tec_id")).get(0));
		response.sendRedirect("pages/search_teacher.jsp");
	}
}
