package cn.nwnu.aics.subject.servlet;

import cn.nwnu.aics.impl.SubjectImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.entity.Subject;

public class UpdateSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 更新课程数据
		SubjectImpl subjectImpl = new SubjectImpl();
		Subject subject = subjectImpl.query("sub_id",
				request.getParameter("sub_id")).get(0);
		subject.setName(request.getParameter("sub_name"));
		subject.setType(request.getParameter("sub_type"));
		subject.setTimes(Integer.parseInt(request.getParameter("sub_times")));
		subjectImpl.update(subject);
		response.sendRedirect("pages/search_subject.jsp");
	}
}
