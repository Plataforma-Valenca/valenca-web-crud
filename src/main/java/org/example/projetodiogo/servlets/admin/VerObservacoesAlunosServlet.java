package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.BoletimDAO;
import org.example.projetodiogo.dao.ObservacaoDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.model.Observacao;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/verObservacoesAlunos")
public class VerObservacoesAlunosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObservacaoDAO observacaoDAO = new ObservacaoDAO();
        int idAlunoParam = Integer.parseInt(req.getParameter("id"));


        try {
            List<Observacao> obsList = observacaoDAO.buscarPorIdAluno(idAlunoParam);

            req.setAttribute("obsList", obsList);

            req.getRequestDispatcher("/WEB-INF/professor/perfilAluno.jsp")
                    .forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
