package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Boletim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CardDisciplinaServlet")
public class CardDisciplinaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Boletim> boletim = new ArrayList<>();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        int idUsuario = (int) session.getAttribute("usuarioId");
        int idAluno = alunoDAO.buscarPorIdUsuario(idUsuario).get().getId();
        int idDisciplina = Integer.parseInt(req.getParameter("idDisciplina"));

        try {
            boletim = disciplinaDAO.visualizarPorDisciplina(idAluno, idDisciplina);

            req.setAttribute("boletimList", boletim);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/aluno/.jsp");
            dispatcher.forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
