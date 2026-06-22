package cn.nwnu.aics.student.servlet;

import cn.nwnu.aics.impl.ClassesImpl;
import cn.nwnu.aics.impl.StudentImpl;
import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Classes;
import cn.nwnu.aics.entity.Operator;
import cn.nwnu.aics.entity.Student;
import cn.nwnu.aics.entity.Teacher;

public class SearchTeacherClassServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentImpl studentImpl = new StudentImpl();
		ClassesImpl classesImpl = new ClassesImpl();
		TeacherImpl teacherImpl = new TeacherImpl();
		Operator operator;
		Classes classes;
		List<Student> list_student;
		HttpSession session = request.getSession();
		// 老师查找自己的班级学生
		operator = (Operator) request.getSession().getAttribute("log_operator");
		Teacher teacher = teacherImpl.query("ope_id", operator.getId() + "")
				.get(0);
		if (classesImpl.query("cla_tec", teacher.getName()).size() <= 0) {
			response.sendRedirect("pages/noclass.jsp");
		} else {
			classes = classesImpl.query("cla_tec", teacher.getName()).get(0);

			list_student = studentImpl.query("cla_id", classes.getId() + "");
			session.setAttribute("list_student", list_student);
			session.setAttribute("classes", classes);
			session.setAttribute("teacher", teacher);
			session.setAttribute("studentNum", list_student.size());
			response.sendRedirect("pages/search_classmate.jsp");
		}

	}
}
