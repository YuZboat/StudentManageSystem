package cn.nwnu.aics.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter
{
	public void destroy()
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		// 如果还没有登录的话跳回登录页面
		String isLogin = (String) ((HttpServletRequest) request).getSession().getAttribute("isLogin");
		
		if (isLogin == null || isLogin.equals(""))
		{
			HttpServletRequest req = (HttpServletRequest) request;
			((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
		}
		else
			chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
	}
}
