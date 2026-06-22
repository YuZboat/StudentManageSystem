package cn.nwnu.aics.teacher.servlet;

import cn.nwnu.aics.impl.OperatorImpl;
import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.entity.Operator;
import cn.nwnu.aics.entity.Teacher;

public class UpdateTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TeacherImpl teacherImpl = new TeacherImpl();
		OperatorImpl operatorImpl = new OperatorImpl();

		Teacher teacher = teacherImpl.query("tec_id",
				request.getParameter("tec_id")).get(0);
		Operator operator = operatorImpl.query("ope_id",
				teacher.getOperator().getId() + "").get(0);

		teacher.setName(request.getParameter("tec_name"));
		teacher.setBirth(request.getParameter("tec_birth"));
		teacher.setSex(request.getParameter("tec_sex").equals("male") ? "男" : "女");
		teacher.setMajor(request.getParameter("tec_major"));
		teacher.setPhone(request.getParameter("tec_phone"));
		operator.setName(request.getParameter("ope_name"));
		operator.setPwd(request.getParameter("ope_pwd"));

		operatorImpl.update(operator);
		teacher.setOperator(operator);
		teacherImpl.update(teacher);

		request.getSession().setAttribute("message", "修改教师信息成功！");
		response.sendRedirect("pages/update_teacher.jsp");
	}
}
