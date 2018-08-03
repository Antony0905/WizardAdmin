package com.everis.wizardadmin.utils;

import com.everis.wizardadmin.model.Query;

public class QueryUtils {

	public QueryUtils() {

	}

	public static Query validarPagina(String pagina, Query qr) {

		if (pagina == null) {

		} else {
			if (pagina.toUpperCase().equals("PROXIMO")) {
				qr.proximaPagina(qr.getPagInicio(), qr.getPagFim());
			} else if (pagina.toUpperCase().equals("ANTERIOR")) {
				if (qr.getPagInicio() <= 1 || qr.getPagFim() <= 100) {
					qr.setPagInicio(1);
					qr.setPagFim(100);
				} else {
					qr.paginaAnterior(qr.getPagInicio(), qr.getPagFim());
				}
			}
		}

		return qr;
	}

}