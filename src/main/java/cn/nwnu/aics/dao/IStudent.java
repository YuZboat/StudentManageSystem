package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Student;

public interface IStudent {
	int add(Student student);

	void delete(Student student);

	void update(Student student);

	List<Student> query(String type, String value);

	List<Student> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
