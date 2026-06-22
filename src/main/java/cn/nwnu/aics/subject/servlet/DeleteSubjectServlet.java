package cn.nwnu.aics.subject.servlet;

import cn.nwnu.aics.impl.SubjectImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 删除数据
		SubjectImpl subjectImpl = new SubjectImpl();
		subjectImpl.delete(subjectImpl.query("sub_id",
				request.getParameter("sub_id")).get(0));
		response.sendRedirect("pages/search_subject.jsp");

	}
}
