package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Subject;

public interface ISubject {
	int add(Subject subject);

	void delete(Subject subject);

	void update(Subject subject);

	List<Subject> query(String type, String value);

	List<Subject> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
