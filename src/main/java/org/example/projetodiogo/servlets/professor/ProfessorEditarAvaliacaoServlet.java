package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.*;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Avaliacao;

import java.io.IOException;

@WebServlet("/professor/editarAvaliacao")
public class ProfessorEditarAvaliacaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        int idAluno = Integer.parseInt(req.getParameter("idAluno"));


        String descricao = req.getParameter("descricao");
        Double valor = Double.parseDouble(req.getParameter("valor"));
        int semestre = Integer.parseInt(req.getParameter("semestre"));

        try {
            Avaliacao avaliacao = new Avaliacao(descricao, valor, semestre);

            avaliacaoDAO.atualizar(avaliacao);

            req.getSession().setAttribute("mensagemSucesso", "Observação editada com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/professor/verPerfilAluno?idAluno=" + idAluno);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}

