package com.library.service;

import java.util.List;

import com.library.database.RecordDao;
import com.library.model.Record;

public class RecordService {

	RecordDao recordDao;

	public RecordService() {
		recordDao = RecordDao.getInstance();
	}

	
	public List<Record> fetchRecords() {
		
		return recordDao.fetchRecords();
	}

}
