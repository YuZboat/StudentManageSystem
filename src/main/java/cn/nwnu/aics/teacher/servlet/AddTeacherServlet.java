package cn.nwnu.aics.teacher.servlet;

import cn.nwnu.aics.impl.OperatorImpl;
import cn.nwnu.aics.impl.RoleImpl;
import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.entity.Operator;
import cn.nwnu.aics.entity.Teacher;

public class AddTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OperatorImpl operatorImpl = new OperatorImpl();
		TeacherImpl teacherImpl = new TeacherImpl();
		RoleImpl roleImpl = new RoleImpl();

		Operator operator = new Operator();
		operator.setName(request.getParameter("ope_name"));
		operator.setPwd(request.getParameter("ope_pwd"));
		operator.setRole(roleImpl.query("rol_id", "2").get(0));
		operator = operatorImpl.add(operator);

		Teacher teacher = new Teacher();
		teacher.setOperator(operator);
		teacher.setName(request.getParameter("tec_name"));
		teacher.setBirth(request.getParameter("tec_birth"));
		teacher.setSex(request.getParameter("tec_sex").equals("male") ? "男" : "女");
		teacher.setMajor(request.getParameter("tec_major"));
		teacher.setPhone(request.getParameter("tec_phone"));

		int i = teacherImpl.add(teacher);
		if (i == 1) {
			request.getSession().setAttribute("message", "添加教师成功！");
		} else {
			request.getSession().setAttribute("message", "添加教师失败！");
		}
		response.sendRedirect("pages/add_teacher.jsp");
	}
}
