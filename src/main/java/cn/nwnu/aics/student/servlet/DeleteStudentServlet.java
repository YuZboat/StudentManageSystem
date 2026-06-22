package cn.nwnu.aics.student.servlet;

import cn.nwnu.aics.impl.StudentImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentImpl studentImpl = new StudentImpl();
		// 删除学生
		studentImpl.delete(studentImpl.query("stu_id",
				request.getParameter("stu_id")).get(0));
		response.sendRedirect("pages/search_student.jsp");
	}
}
