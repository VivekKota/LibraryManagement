package com.library.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.library.model.Record;

public class RecordDao {

	private static RecordDao recordDao;

	private RecordDao() {
	}

	public static RecordDao getInstance() {

		if (recordDao == null) {
			recordDao = new RecordDao();
		}
		return recordDao;
	}

	// Fetch all records
	public List<Record> fetchRecords() {

		try {

			Connection con = JDBCConnection.connect();
			Statement st = con.createStatement();
			String sql = "Select * from record";
			ResultSet rs = st.executeQuery(sql);
			List<Record> records = new ArrayList<Record>();
			while (rs.next()) {

				int recordId = rs.getInt(1);
				int userId = rs.getInt("userId");
				int bookId = rs.getInt("bookId");
				String status = rs.getString("status");
				Timestamp date = rs.getTimestamp("date");
				Record record = new Record(recordId, userId, bookId, status, date);
				records.add(record);
			}
			return records;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
