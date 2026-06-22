package cn.nwnu.aics.classes.servlet;

import cn.nwnu.aics.impl.MajorImpl;
import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Major;
import cn.nwnu.aics.entity.Teacher;

public class PlanAddClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 准备添加班级
		TeacherImpl teacherImpl = new TeacherImpl();
		MajorImpl majorImpl = new MajorImpl();
		List<Teacher> list_teacher;
		List<Major> list_major;
		HttpSession session = request.getSession();
		list_major = majorImpl.query("all", "all");
		list_teacher = teacherImpl.query("all", "all");
		session.setAttribute("list_teacher", list_teacher);
		session.setAttribute("list_major", list_major);
		response.sendRedirect("pages/add_classes.jsp");
	}
}
