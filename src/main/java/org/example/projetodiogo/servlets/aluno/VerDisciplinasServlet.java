package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Disciplina;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/aluno/VerDisciplinas", "/aluno/CardDisciplinaServlet"})
public class VerDisciplinasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ArrayList<Disciplina> disciplinaList;

        try {
            disciplinaList = disciplinaDAO.visualizarDisciplinas();

            req.setAttribute("disciplinaList", disciplinaList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/aluno/cardsDisciplina.jsp");
            dispatcher.forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}