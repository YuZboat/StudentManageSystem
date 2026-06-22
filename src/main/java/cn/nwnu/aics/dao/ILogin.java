package cn.nwnu.aics.dao;

import jakarta.servlet.http.HttpServletRequest;

import cn.nwnu.aics.entity.Operator;

public interface ILogin {
	String login(HttpServletRequest request, Operator operator);
}
