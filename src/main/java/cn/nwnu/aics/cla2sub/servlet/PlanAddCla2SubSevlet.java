package cn.nwnu.aics.cla2sub.servlet;

import cn.nwnu.aics.impl.ClassesImpl;
import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Classes;
import cn.nwnu.aics.entity.Teacher;

public class PlanAddCla2SubSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 添加班级课程前查询可选班级
		TeacherImpl teacherImpl = new TeacherImpl();
		ClassesImpl classesImpl = new ClassesImpl();
		List<Teacher> list_teacher;
		List<Classes> list_classes;
		HttpSession session = request.getSession();
		list_classes = classesImpl.query("all", "all");
		list_teacher = teacherImpl.query("all", "all");
		session.setAttribute("list_teacher", list_teacher);
		session.setAttribute("list_classes", list_classes);
		response.sendRedirect("pages/add_classes_subject.jsp");

	}
}
