package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Pessoa;
import persistence.PessoaDao;
import persistence.GenericDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PessoaServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String botao = request.getParameter("botao");

		GenericDao gDao = new GenericDao();
		PessoaDao cDao = new PessoaDao(gDao);

		Pessoa pessoa = new Pessoa();
		List<Pessoa> pessoas = new ArrayList<>();
		String erro = "";
		String saida = "";

		try {
			if (botao.equalsIgnoreCase("listar")) {
				pessoas = cDao.list();
			} else {
				pessoa.setId(Integer.parseInt(request.getParameter("idPessoa")));
				if (botao.equalsIgnoreCase("consultar") || botao.equalsIgnoreCase("excluir")) {
					if (botao.equalsIgnoreCase("consultar")) {
						pessoa = cDao.select(pessoa);
					} else {
						saida = cDao.delete(pessoa);
						pessoa = new Pessoa();
					}
				} else {
					pessoa.setNome(request.getParameter("nomePessoa"));
					pessoa.setTelefoneFixo(request.getParameter("telefoneFixoPessoa"));
					pessoa.setTelefoneCelular(request.getParameter("telefoneCelularPessoa"));
					if (botao.equalsIgnoreCase("inserir")) {
						saida = cDao.insert(pessoa);
						pessoa = new Pessoa();
					} else {
						saida = cDao.update(pessoa);
						pessoa = new Pessoa();
					}
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("pessoa", pessoa);
			request.setAttribute("pessoas", pessoas);

			RequestDispatcher dispatcher = request.getRequestDispatcher("pessoa.jsp");
			dispatcher.forward(request, response);
		}
	}

}
