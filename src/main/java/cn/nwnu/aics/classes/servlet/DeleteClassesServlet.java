package cn.nwnu.aics.classes.servlet;

import cn.nwnu.aics.impl.ClassesImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 删除班级
		ClassesImpl classesImpl = new ClassesImpl();
		classesImpl.delete(classesImpl.query("cla_id",
				request.getParameter("cla_id")).get(0));
		response.sendRedirect("pages/search_classes.jsp");

	}
}
