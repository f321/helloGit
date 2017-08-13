package com.jdbcexample.domain;

import static org.hamcrest.CoreMatchers.containsString;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PostCrud {

	String user = "root";
	String pass = "root";
	String conString = "jdbc:mysql://localhost:3306/test";

	Connection conn;
	Statement stmt;
	ResultSet rs;

	public PostCrud() {
		try {
			conn = DriverManager.getConnection(conString, user, pass);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Post> Ucitaj() {
		List<Post> list = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(conString, user, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tbl_post;");
			while (rs.next()) {
				list.add(new Post(rs.getInt("id"), rs.getString("message")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public void unesi(Post p) throws Exception {
		stmt.executeUpdate("INSERT INTO tbl_post(message) Values('" + p.getMessage() + "')");
	}

	public void update(Post p) throws SQLException {
		stmt.executeUpdate("UPDATE tbl_post SET message='" + p.getMessage() + "' WHERE id=" + p.getId() + ";");
	}

	public Post pronadji(int id) {
		for (Post p : Ucitaj()) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	public void obrisi(int id) throws SQLException {
		stmt.executeUpdate("DELETE FROM tbl_post WHERE id="+id+";");
		
	}
}
