package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DTO.DisciplinasResumoDtoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.DTO.DisciplinasResumoDTO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/aluno/VerDisciplinas")
public class AlunoVerDisciplinasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DisciplinasResumoDtoDAO disciplinaDAO = new DisciplinasResumoDtoDAO();
        ArrayList<DisciplinasResumoDTO> disciplinaList;

        try {
            disciplinaList = disciplinaDAO.visualizarDisciplinasResumo();

            req.setAttribute("disciplinaList", disciplinaList);

            req.setAttribute("activePage", "disciplinas");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/aluno/aluno-ver-disciplinas.jsp");
            dispatcher.forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}