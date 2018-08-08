package com.everis.wizardadmin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.everis.wizardadmin.constants.WizardConstants;
import com.everis.wizardadmin.model.Query;
import com.everis.wizardadmin.service.QueryService;
import com.everis.wizardadmin.utils.QueryUtils;

@WebServlet("/QueryController")
public class QueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		Query returnQuery = new Query();
		QueryService service = new QueryService();
		String operacao = null;
		String pagina = request.getParameter("pagina");
		returnQuery.setPagInicio(Integer.parseInt(request.getParameter("paginaInicio")));
		returnQuery.setPagFim(Integer.parseInt(request.getParameter("paginaFim")));

		returnQuery = QueryUtils.validarPagina(pagina, returnQuery);
		returnQuery.setSql(request.getParameter("textAreaQuery"));

		try {
			operacao = returnQuery.getSql().toUpperCase().substring(0, 6);
		} catch (Exception e) {
			operacao = "ERROR";
		}

		HttpSession sessao = request.getSession();

		returnQuery.setSql(returnQuery.getSql().replace(";", ""));

		if (operacao.equals(WizardConstants.INSERT) || operacao.equals(WizardConstants.DELETE)
				|| operacao.equals(WizardConstants.UPDATE)) {
			try {
				returnQuery = service.executeUpdate(returnQuery);
				sessao.setAttribute("returnQuery", returnQuery);
				returnQuery.setQueryRetorno(true);
				returnQuery.setMensagem("Query executada com sucesso");
			} catch (Exception e) {
				e.printStackTrace();
				returnQuery.setRetorno(false);
				returnQuery.setMensagem(e.toString());
				if (returnQuery.isDataBaseConnector() == false) {
					returnQuery.setMensagem("Falha na conexão com o banco de dados");
				}
				sessao.setAttribute("returnQuery", returnQuery);
			}
		} else if (operacao.equals(WizardConstants.SELECT)) {
			try {

				returnQuery = service.executeQuery(returnQuery, returnQuery.getPagInicio(), returnQuery.getPagFim());
				returnQuery.setRetorno(true);
				sessao.setAttribute("returnQuery", returnQuery);

			} catch (Exception e) {
				e.printStackTrace();
				returnQuery.setRetorno(false);
				returnQuery.setMensagem(e.toString());
				if (returnQuery.isDataBaseConnector() == false) {
					returnQuery.setMensagem("Falha na conexão com o banco de dados");
				}
				sessao.setAttribute("returnQuery", returnQuery);
			}
		} else {
			returnQuery.setRetorno(false);
			returnQuery.setMensagem(
					"java.sql.SQLSyntaxErrorException: ORA-00933: comando SQL não encerrado adequadamente");
			sessao.setAttribute("returnQuery", returnQuery);
		}

		response.sendRedirect("index.jsp");

	}

}
