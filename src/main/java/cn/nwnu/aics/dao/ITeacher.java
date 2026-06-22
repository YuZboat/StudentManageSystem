package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Teacher;

public interface ITeacher {
	int add(Teacher teacher);

	void delete(Teacher teacher);

	void update(Teacher teacher);

	List<Teacher> query(String type, String value);

	List<Teacher> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
