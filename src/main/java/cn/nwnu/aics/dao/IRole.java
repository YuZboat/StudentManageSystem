package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Role;

public interface IRole {
	void add(Role role);

	void delete(Role role);

	void update(Role role);

	List<Role> query(String type, String value);

	List<Role> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
