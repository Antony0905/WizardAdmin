package com.everis.wizardadmin.service;

import com.everis.wizardadmin.dao.QueryDao;
import com.everis.wizardadmin.model.Query;

public class QueryService {

	private QueryDao db = new QueryDao();

	public Query executeUpdate(Query query) throws Exception {
		return db.executeUpdate(query);
	}

	public Query executeQuery(Query returnQuery, int pagInicio, int pagFim) throws Exception {
		return db.executeQuery(returnQuery, pagInicio, pagFim);
	}

}
