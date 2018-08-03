package com.everis.wizardadmin.model;

import java.util.List;

public class Query {

	String sql;
	String mensagem;
	List<Object> objectList;
	List<String> listColumHeaderName;
	int numeroDeColunas;
	int pagInicio;
	int pagFim;
	boolean retorno;
	boolean queryRetorno;
	boolean dataBaseConnector = true;

	public void proximaPagina(int paginaInicio, int paginaFim) {
		this.pagInicio = pagInicio + 100;
		this.pagFim = pagFim + 100;
	}

	public void paginaAnterior(int paginaInicio, int paginaFim) {
		this.pagInicio = pagInicio - 100;
		this.pagFim = pagFim - 100;
	}

	public int getNumeroDeColunas() {
		return numeroDeColunas;
	}

	public void setNumeroDeColunas(int numeroDeColunas) {
		this.numeroDeColunas = numeroDeColunas;
	}

	public List<Object> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}

	public boolean isRetorno() {
		return retorno;
	}

	public void setRetorno(boolean retorno) {
		this.retorno = retorno;
	}

	public List<String> getListColumHeaderName() {
		return listColumHeaderName;
	}

	public void setListColumHeaderName(List<String> listColumHeaderName) {
		this.listColumHeaderName = listColumHeaderName;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getPagInicio() {
		return pagInicio;
	}

	public void setPagInicio(int pagInicio) {
		this.pagInicio = pagInicio;
	}

	public int getPagFim() {
		return pagFim;
	}

	public void setPagFim(int pagFim) {
		this.pagFim = pagFim;
	}

	public boolean isQueryRetorno() {
		return queryRetorno;
	}

	public void setQueryRetorno(boolean queryRetorno) {
		this.queryRetorno = queryRetorno;
	}

	public boolean isDataBaseConnector() {
		return dataBaseConnector;
	}

	public void setDataBaseConnector(boolean dataBaseConnector) {
		this.dataBaseConnector = dataBaseConnector;
	}

}
