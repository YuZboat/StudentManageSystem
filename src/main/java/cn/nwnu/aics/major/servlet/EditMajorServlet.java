package cn.nwnu.aics.major.servlet;

import cn.nwnu.aics.impl.MajorImpl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Major;

public class EditMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 准备更新专业信息
		MajorImpl majorImpl = new MajorImpl();
		HttpSession session = request.getSession();
		
		Major major = majorImpl.query("maj_id", request.getParameter("maj_id"))
				.get(0);
		session.setAttribute("major", major);
		response.sendRedirect("pages/update_major.jsp");
	}
}
