package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.ObservacaoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Observacao;

import java.io.IOException;

@WebServlet("/professor/EditarObs")
public class EditarObsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idAluno = Integer.parseInt(req.getParameter("idAluno"));
        req.setAttribute("idAluno", idAluno);
        req.getRequestDispatcher("/WEB-INF/professor/editarObservacao.jsp")
                .forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObservacaoDAO obsDAO = new ObservacaoDAO();
        Observacao obs = new Observacao();
        int idAluno = Integer.parseInt(req.getParameter("idAluno"));
        int idObs = Integer.parseInt(req.getParameter("idObservacao"));
        String descricao = req.getParameter("descricao");

        try {
            obs.setDescricao(descricao);
            obsDAO.atualizar(obs);

            req.getSession().setAttribute("mensagemSucesso", "Observação editada com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/professor/verPerfilAluno?idAluno=" + idAluno);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
