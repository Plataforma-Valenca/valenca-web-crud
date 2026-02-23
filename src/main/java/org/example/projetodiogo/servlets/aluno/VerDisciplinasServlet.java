package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.dao.ProfessorDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.model.Professor;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/VerDisciplinas")
public class VerDisciplinasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ArrayList<Disciplina> disciplinasList;

        try {
            disciplinasList = disciplinaDAO.visualizarDisciplinas();

            req.setAttribute("disciplinasList", disciplinasList);

           RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/aluno/listarDisciplinas.jsp");
dispatcher.forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}