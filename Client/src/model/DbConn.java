package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class DbConn {
    private String jdbcUrl = null;
    private String userid = null;
    private String password = null;
    private Connection conn;

    public void getDBConnection() throws SQLException {
        userid = "G4_midob";
        password = "hokousharingan";
        jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ora2016";
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn = ds.getConnection(userid, password);
    }

    public static void main(String[] args) throws SQLException {
        DbConn db = new DbConn();
        db.getDBConnection();
        Statement stmt = db.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            System.out.printf("%d\t%-32s\t%-32s\n", resultSet.getInt("user_id"), resultSet.getString("login"),
                              resultSet.getString("password"));

        }


    }

    public Connection getConn() {
        return conn;
    }
}
