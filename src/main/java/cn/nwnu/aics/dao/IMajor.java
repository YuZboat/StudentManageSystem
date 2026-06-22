package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Major;

public interface IMajor {
	int add(Major major);

	void delete(Major major);

	void update(Major major);

	List<Major> query(String type, String value);

	List<Major> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
