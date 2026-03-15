package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Turma;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/professor/verTurmas")
public class ProfessorVerTurmasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        TurmasDAO turmasDAO = new TurmasDAO();

        try {

            List<Turma> turmasList = turmasDAO.buscarTurmas();

            req.setAttribute("turmasList", turmasList);

            req.getRequestDispatcher("/WEB-INF/professor/professor-ver-turmas.jsp")
                    .forward(req, resp);

        } catch (SQLException | DataAccessException e) {
            throw new ServletException("Erro ao buscar turmas", e);
        }
    }
}