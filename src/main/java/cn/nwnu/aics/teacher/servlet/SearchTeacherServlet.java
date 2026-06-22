package cn.nwnu.aics.teacher.servlet;

import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;
import cn.nwnu.aics.entity.Teacher;

public class SearchTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TeacherImpl teacherImpl = new TeacherImpl();
		String search_type = request.getParameter("search_type");
		String search_value = java.net.URLDecoder.decode(
				request.getParameter("value"), "UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		List<Teacher> list_teacher = new ArrayList<Teacher>();
		if ("all".equals(search_type)) {
			list_teacher = teacherImpl.query("all", search_value, page);
		} else {
			list_teacher = teacherImpl.query(search_type, search_value, page);
		}
		response.getWriter().write(
				JSONSerializer.toJSON(list_teacher).toString());
	}
}
