package com.everis.wizardadmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.everis.wizardadmin.connectionfactory.ConnectionFactory;
import com.everis.wizardadmin.model.Query;

public class QueryDao {
	private Connection connection;

	public QueryDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public Query executeUpdate(Query query) throws Exception {

		if (connection == null) {
			query.setDataBaseConnector(false);
		}

		PreparedStatement ps = null;
		ps = connection.prepareStatement(query.getSql());
		ps.executeUpdate();

		return query;
	}

	public Query executeQuery(Query returnQuery, int pagInicio, int pagFim) throws Exception {

		if (connection == null) {
			returnQuery.setDataBaseConnector(false);
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = connection.prepareStatement("SELECT * FROM ( SELECT topn.*, ROWNUM rnum FROM ( " + returnQuery.getSql()
				+ " ) topn WHERE ROWNUM <= ? ) WHERE rnum  >= ?");

		ps.setInt(1, pagFim);
		ps.setInt(2, pagInicio);
		rs = ps.executeQuery();
		returnQuery.setRetorno(false);
		List<Object> object = new ArrayList<Object>();
		List<Object> objectList = new ArrayList<Object>();

		ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
		returnQuery.setNumeroDeColunas(metaData.getColumnCount());

		List<String> listColumHeaderName = new ArrayList<String>();

		for (int i = 1; i <= returnQuery.getNumeroDeColunas(); i++) {
			listColumHeaderName.add(metaData.getColumnName(i));
		}

		while (rs.next()) {
			for (int coluna = 1; coluna <= returnQuery.getNumeroDeColunas(); coluna++) {
				object.add(rs.getObject(coluna));
			}

			objectList.add(object);
			object = new ArrayList<Object>();
		}

		returnQuery.setObjectList(objectList);
		returnQuery.setListColumHeaderName(listColumHeaderName);
		returnQuery.setRetorno(true);
		return returnQuery;

	}
}
