package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Classes;

public interface IClasses {
	Classes add(Classes classes);

	void delete(Classes classes);

	void update(Classes classes);

	List<Classes> query(String type, String value);

	List<Classes> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
