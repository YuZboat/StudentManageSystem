package cn.nwnu.aics.teacher.servlet;

import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetCountPageServlet extends HttpServlet {
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
		int countPage;
		if ("all".equals(search_type)) {
			countPage = teacherImpl.getCountPage("all", search_value);
		} else {
			countPage = teacherImpl.getCountPage(search_type, search_value);
		}
		response.getWriter().write(countPage + "");
	}
}
