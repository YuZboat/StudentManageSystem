package cn.nwnu.aics.student.servlet;

import cn.nwnu.aics.impl.ClassesImpl;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Classes;

public class PlanAddStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClassesImpl classesImpl = new ClassesImpl();
		List<Classes> list_classes;
		HttpSession session = request.getSession();
		// 准备添加学生，查询可用班级
		list_classes = classesImpl.query("all", "all");
		session.setAttribute("list_classes", list_classes);
		response.sendRedirect("pages/add_student.jsp");
	}
}
