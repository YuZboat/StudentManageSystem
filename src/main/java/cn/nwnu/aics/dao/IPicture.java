package cn.nwnu.aics.dao;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.entity.Student;

public interface IPicture {
	void check(ServletConfig servletConfig, HttpServletRequest request,
			HttpServletResponse response, Student student);

	void delete(ServletConfig servletConfig, HttpServletRequest request,
			HttpServletResponse response, Student student);

	void upload(ServletConfig servletConfig, HttpServletRequest request,
			Student student);
}
