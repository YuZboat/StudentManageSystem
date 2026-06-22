package cn.nwnu.aics.student.servlet;

import cn.nwnu.aics.impl.ClassesImpl;
import cn.nwnu.aics.impl.StudentImpl;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Classes;
import cn.nwnu.aics.entity.Student;

public class EditStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentImpl studentImpl = new StudentImpl();
		ClassesImpl classesImpl = new ClassesImpl();
		Student student;
		List<Classes> list_classes;
		HttpSession session = request.getSession();
		// 准备更新学生信息
		student = studentImpl.query("stu_id", request.getParameter("stu_id"))
				.get(0);
		list_classes = classesImpl.query("all", "all");
		session.setAttribute("student", student);
		session.setAttribute("list_classes", list_classes);
		response.sendRedirect("pages/update_student.jsp");
	}
}
