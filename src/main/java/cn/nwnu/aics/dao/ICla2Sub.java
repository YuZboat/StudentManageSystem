package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Cla2Sub;

public interface ICla2Sub {
	int add(Cla2Sub cla2Sub);

	void delete(Cla2Sub cla2Sub);

	void update(Cla2Sub cla2Sub);

	Cla2Sub findCla2sub(int cla_id, int tec_id, int sub_id);

	List<Cla2Sub> query(String type, String value);

	List<Cla2Sub> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
