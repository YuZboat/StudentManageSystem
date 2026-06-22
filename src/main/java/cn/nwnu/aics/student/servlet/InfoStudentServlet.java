package cn.nwnu.aics.student.servlet;

import cn.nwnu.aics.impl.StudentImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Operator;
import cn.nwnu.aics.entity.Student;

public class InfoStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentImpl studentImpl = new StudentImpl();
		Operator operator;
		Student student;
		HttpSession session = request.getSession();

		// 查询自己的信息
		operator = (Operator) request.getSession().getAttribute("log_operator");
		student = studentImpl.query("ope_id", operator.getId() + "").get(0);
		session.setAttribute("student_me", student);
		response.sendRedirect("pages/info_student.jsp");

	}

}
