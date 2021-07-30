package com.library.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public void addRecord(Record record) {
		try {
			Connection con = JDBCConnection.connect();

			PreparedStatement pst = con
					.prepareStatement("insert into record(userId,bookId,status,date) values(?,?,?,?)");
			pst.setInt(1, record.getUserId());
			pst.setInt(2, record.getBookId());
			pst.setString(3, record.getStatus());
			pst.setTimestamp(4, record.getDate());

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {
			System.out.println("Error in adding Records");
			e.printStackTrace();
		}

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

		} catch (Exception e) {

			System.out.println("Error in fectching the records ");
			e.printStackTrace();
		}
		return null;

	}

	public Record findLatestRecord(int userId) {

		try {

			Connection con = JDBCConnection.connect();

			PreparedStatement pst = con.prepareStatement(
					"Select * from record where userId = ?  order by recordId desc limit 1");
			pst.setInt(1, userId);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				int recordId = rs.getInt(1);
				int userId1 = rs.getInt("userId");
				int bookId1 = rs.getInt("bookId");
				String status = rs.getString("status");
				Timestamp date = rs.getTimestamp("date");

				Record record = new Record(recordId, userId1, bookId1, status, date);
				return record;
			}

		} catch (Exception e) {

			System.out.println("Error in fectching the latest records of the user ");
			e.printStackTrace();
		}

		return null;
	}

	public List<Record> findUserRecords(int userId) {
		
		try {

			Connection con = JDBCConnection.connect();

			PreparedStatement pst = con.prepareStatement("Select * from record where userId= ?");
			pst.setInt(1, userId);
			
			ResultSet rs = pst.executeQuery();

			List<Record> records = new ArrayList<Record>();
			while (rs.next()) {

				int recordId = rs.getInt(1);
				int userId1 = rs.getInt("userId");
				int bookId = rs.getInt("bookId");
				String status = rs.getString("status");
				Timestamp date = rs.getTimestamp("date");
				Record record = new Record(recordId, userId1, bookId, status, date);
				records.add(record);
			}
			return records;

		} catch (Exception e) {

			System.out.println("Error in fectching the records of the user");
			e.printStackTrace();
		}
		return null;
	}

}
