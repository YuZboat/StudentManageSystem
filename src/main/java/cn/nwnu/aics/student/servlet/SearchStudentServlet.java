package cn.nwnu.aics.student.servlet;

import cn.nwnu.aics.impl.StudentImpl;
import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;
import cn.nwnu.aics.entity.Operator;
import cn.nwnu.aics.entity.Student;
import cn.nwnu.aics.entity.Teacher;

public class SearchStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentImpl studentImpl = new StudentImpl();
		TeacherImpl teacherImpl = new TeacherImpl();
		Operator operator;
		Teacher teacher;
		List<Student> list_student;

		// AJAX异步查询学生
		operator = (Operator) request.getSession().getAttribute("log_operator");
		int ope_rol_id = operator.getRole().getId();
		String search_type = request.getParameter("search_type");
		String search_value = java.net.URLDecoder.decode(request
				.getParameter("value"), "UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		list_student = new ArrayList<Student>();

		if (ope_rol_id == 1) {
			// 管理员的查询
			if (search_type.equals("stu_all")) {
				list_student = studentImpl.query("stu_all", search_value + "",
						page);
			} else if (search_type.equals("stu_no")) {
				list_student = studentImpl.query("stu_no", search_value + "",
						page);
			} else if (search_type.equals("stu_name")) {
				list_student = studentImpl.query("stu_name", search_value + "",
						page);
			} else if (search_type.equals("cla_name")) {
				list_student = studentImpl.query("cla_name", search_value + "",
						page);
			} else if (search_type.equals("maj_name")) {
				list_student = studentImpl.query("maj_name", search_value + "",
						page);
			}
		} else if (ope_rol_id == 2) {
			// 老师的查询
			teacher = teacherImpl.query("ope_id", operator.getId()/* 3 */+ "")
					.get(0);
			if (search_type.equals("stu_all")) {
				list_student = studentImpl.query("tec_stu_all", teacher
						.getName(), page);
			} else if (search_type.equals("stu_no")) {
				list_student = studentImpl.query("tec_stu_no", teacher
						.getName()
						+ "_" + search_value, page);
			} else if (search_type.equals("stu_name")) {
				list_student = studentImpl.query("tec_stu_name", teacher
						.getName()
						+ "_" + search_value, page);
			} else if (search_type.equals("cla_name")) {
				list_student = studentImpl.query("tec_cla_name", teacher
						.getName()
						+ "_" + search_value, page);
			} else if (search_type.equals("maj_name")) {
				list_student = studentImpl.query("tec_maj_name", teacher
						.getName()
						+ "_" + search_value, page);
			}
		}
		response.getWriter().write(
				JSONSerializer.toJSON(list_student).toString());

	}
}
