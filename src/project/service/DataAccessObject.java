package project.service;

import java.util.List;

public interface DataAccessObject<T> {
	void addData(T data);
	void updateData(T data);
	void removeData(String code);
	T getData(String code);
	List<T> getDataList();
	int getDataListSize();
}
