package cn.nwnu.aics.dao;

import java.util.List;

import cn.nwnu.aics.entity.Score;

public interface IScore {
	void add(Score score);

	void delete(Score score);

	void update(Score score);

	List<Score> query(String type, String value);

	List<Score> query(String type, String value, int currentPage);

	int getCountPage(String type, String value);
}
