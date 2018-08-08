package com.everis.wizardadmin.connectionfactory;

import java.sql.*;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionFactory {

	/*
	 * private String user = "WizardAdmin"; private String password = "WizardAdmin";
	 * private String url = "jdbc:oracle:thin:@localhost:1521:xe"; private String
	 * Driver = "oracle.jdbc.driver.OracleDriver";
	 */

	private String user = "homologacao";
	private String password = "homologacao";
	private String url = "jdbc:oracle:thin:@10.129.181.149:1521:FOPAQA1";
	private String Driver = "oracle.jdbc.driver.OracleDriver";

	/*public Connection getConnection() {

		try {
			Class.forName(Driver);
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}*/

	public Connection getConnection() {

		Context ctx = null;
		Hashtable ht = new Hashtable();
		Connection conn = null;
		try {
			ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			ctx = new InitialContext(ht);
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("FOPAQA1_Pool");
			conn = ds.getConnection();
			while (conn.isClosed()) {
				conn = ds.getConnection();
			}
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {

		}
		return conn;

	}

}