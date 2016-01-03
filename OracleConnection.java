package Project;

import java.sql.*;

import javax.swing.JOptionPane;

import oracle.jdbc.pool.OracleDataSource;

public class OracleConnection {
	Connection conn = null;
	public static Connection dbConnector() {
		OracleDataSource ds;
		try {
			ds = new oracle.jdbc.pool.OracleDataSource();
			 ds.setURL("jdbc:oracle:thin:@127.0.0.1:1521: ACAD111");
			 Connection conn = ds.getConnection("skandim1", "SHIva2030");
			 return conn;
		
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	     
		
		
	}

}
