package cn.nwnu.aics.major.servlet;

import cn.nwnu.aics.impl.MajorImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.entity.Major;

public class UpdateMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 更新专业信息
		MajorImpl majorImpl = new MajorImpl();
		Major major = majorImpl.query("maj_id", request.getParameter("maj_id"))
				.get(0);
		major.setName(request.getParameter("maj_name"));
		major.setPrin(request.getParameter("maj_prin"));
		major.setLink(request.getParameter("maj_link"));
		major.setPhone(request.getParameter("maj_phone"));
		majorImpl.update(major);
		response.sendRedirect("pages/search_major.jsp");
	}
}
