package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Privilege;

public interface IPrivilege {
	void add(Privilege privilege);

	void delete(Privilege privilege);

	void update(Privilege privilege);

	List<Privilege> query(String type, String value);

	List<Privilege> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
