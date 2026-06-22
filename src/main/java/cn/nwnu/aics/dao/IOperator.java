package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Operator;

public interface IOperator {
	Operator add(Operator operator);

	void delete(Operator operator);

	void update(Operator operator);

	List<Operator> query(String type, String value);

	List<Operator> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
